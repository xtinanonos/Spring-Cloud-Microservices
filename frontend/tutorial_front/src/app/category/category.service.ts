import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Category } from './model/Category';
import { CATEGORY_DATA } from './model/mock-categories';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(
    private http: HttpClient
  ) { }

  // solicitudes que se lanzan contra el controller del backend a traves de una URL http

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>('http://localhost:8080/category');
  }

  saveCategory(category: Category): Observable<Category> {
    let url = 'http://localhost:8080/category';
        if (category.id != null) url += '/'+category.id;

        return this.http.put<Category>(url, category);
  }

  deleteCategory(idCategory : number): Observable<any> {
    return this.http.delete('http://localhost:8080/category/'+idCategory);
  } 
}
