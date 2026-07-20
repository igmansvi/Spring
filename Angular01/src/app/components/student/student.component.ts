import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { StudentService } from '../../core/services/student.service';
import { Student } from '../../core/models/student.model';

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './student.component.html',
  styleUrl: './student.component.css',
})
export class StudentComponent implements OnInit {
  private studentService = inject(StudentService);
  private fb = inject(FormBuilder);
  private cdr = inject(ChangeDetectorRef);

  students: Student[] = [];
  filteredStudents: Student[] = [];

  loading = true;
  showForm = false;
  editingId: number | null = null;
  formError = '';

  bannerMessage = '';
  bannerError = false;
  showBannerFlag = false;
  bannerTimeout: any;

  studentForm: FormGroup = this.fb.group({
    name: ['', [Validators.required, Validators.maxLength(80)]],
    age: ['', [Validators.required, Validators.min(1), Validators.max(120)]],
    department: ['', Validators.required]
  });

  searchControl = new FormControl('');

  ngOnInit(): void {
    this.fetchAll();

    this.searchControl.valueChanges.subscribe(query => {
      this.applyFilter(query || '');
    });
  }

  showBanner(message: string, isError: boolean) {
    this.bannerMessage = message;
    this.bannerError = isError;
    this.showBannerFlag = true;
    this.cdr.markForCheck();

    if (this.bannerTimeout) {
      clearTimeout(this.bannerTimeout);
    }
    this.bannerTimeout = setTimeout(() => {
      this.showBannerFlag = false;
      this.cdr.markForCheck();
    }, 3500);
  }

  fetchAll(): void {
    this.loading = true;
    this.studentService.getStudents().subscribe({
      next: (res) => {
        if (res.success) {
          this.students = res.data || [];
        } else {
          this.students = [];
          this.showBanner(res.message || 'Could not load the register.', true);
        }
        this.applyFilter(this.searchControl.value || '');
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.students = [];
        this.showBanner('Could not reach the server. Is it running on port 8080?', true);
        this.applyFilter(this.searchControl.value || '');
        this.loading = false;
        this.cdr.markForCheck();
      }
    });
  }

  applyFilter(query: string): void {
    const q = query.trim().toLowerCase();
    this.filteredStudents = q
      ? this.students.filter((s) =>
          s.name.toLowerCase().includes(q) || s.department.toLowerCase().includes(q))
      : this.students;
    this.cdr.markForCheck();
  }

  openForm(mode: 'new' | 'edit', student?: Student) {
    this.showForm = true;
    this.formError = '';

    if (mode === 'edit' && student) {
      this.editingId = student.id || null;
      this.studentForm.patchValue({
        name: student.name,
        age: student.age,
        department: student.department
      });
    } else {
      this.editingId = null;
      this.studentForm.reset();
    }
    this.cdr.markForCheck();
  }

  closeForm() {
    this.showForm = false;
    this.studentForm.reset();
    this.editingId = null;
    this.formError = '';
    this.cdr.markForCheck();
  }

  toggleForm() {
    if (this.showForm) {
      this.closeForm();
    } else {
      this.openForm('new');
    }
  }

  deleteStudent(id?: number) {
    if (!id) return;
    if (!confirm('Remove this student from the register? This cannot be undone.')) return;

    this.studentService.deleteStudent(id).subscribe({
      next: (res) => {
        if (res.success) {
          this.showBanner(res.message || 'Student removed.', false);
          this.fetchAll();
        } else {
          this.showBanner(res.message || 'Could not remove this record.', true);
        }
        this.cdr.markForCheck();
      },
      error: () => {
        this.showBanner('Could not reach the server.', true);
        this.cdr.markForCheck();
      }
    });
  }

  onSubmit() {
    if (this.studentForm.invalid) {
      this.studentForm.markAllAsTouched();
      return;
    }

    this.formError = '';
    this.studentForm.disable();
    const payload: Student = this.studentForm.value;

    if (this.editingId) {
      this.studentService.updateStudent(this.editingId, payload).subscribe({
        next: (res) => {
          if (res.success) {
            this.showBanner(res.message || 'Entry updated.', false);
            this.closeForm();
            this.fetchAll();
          } else {
            this.formError = res.message || 'Something went wrong. Check the details and try again.';
          }
          this.studentForm.enable();
          this.cdr.markForCheck();
        },
        error: () => {
          this.formError = 'Could not reach the server.';
          this.studentForm.enable();
          this.cdr.markForCheck();
        }
      });
    } else {
      this.studentService.createStudent(payload).subscribe({
        next: (res) => {
          if (res.success) {
            this.showBanner(res.message || 'Entry added.', false);
            this.closeForm();
            this.fetchAll();
          } else {
            this.formError = res.message || 'Something went wrong. Check the details and try again.';
          }
          this.studentForm.enable();
          this.cdr.markForCheck();
        },
        error: () => {
          this.formError = 'Could not reach the server.';
          this.studentForm.enable();
          this.cdr.markForCheck();
        }
      });
    }
  }

  padStart(value: number | undefined, targetLength: number, padString: string): string {
    if (value === undefined || value === null) return '';
    return String(value).padStart(targetLength, padString);
  }

  scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}
