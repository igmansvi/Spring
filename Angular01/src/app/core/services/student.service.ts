import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Student } from '../models/student.model';
import { ApiResponse } from '../models/api-response.model';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private http = inject(HttpClient);

  private readonly baseApiUrl = 'http://localhost:8080';

  getStudents() {
    return this.http.get<ApiResponse<Student[]>>(this.baseApiUrl + '/student');
  }

  getStudentById(id: number) {
    return this.http.get<ApiResponse<Student>>(this.baseApiUrl + '/student/' + id);
  }

  createStudent(student: Student) {
    return this.http.post<ApiResponse<Student>>(this.baseApiUrl + '/student/create', student);
  }

  updateStudent(id: number, student: Student) {
    return this.http.put<ApiResponse<Student>>(this.baseApiUrl + '/student/' + id, student);
  }

  deleteStudent(id: number) {
    return this.http.delete<ApiResponse<null>>(this.baseApiUrl + '/student/' + id);
  }
}
