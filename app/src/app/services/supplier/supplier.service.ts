import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Supplier } from '../../models/supplier/supplier';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  constructor(private http: HttpClient) { }


  private url: string = 'http://localhost:8080/supplier';

  createSupplier(supplier: Supplier): Observable<Supplier> {
    return this.http.post<Supplier>(this.url, supplier);
  }

  listAllSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(this.url);
  }

  selectSupplierById(id: number): Observable<Supplier> {
    const url = `${this.url}/${id}`;
    return this.http.get<Supplier>(url);
  }

  updateCliente(supplier: Supplier): Observable<Supplier> {
    return this.http.put<Supplier>(this.url, supplier);
  }

  deleteSupplier(id: number): Observable<Supplier> {
    const url = `${this.url}/${id}`;
    return this.http.delete<Supplier>(url);
  }

  getSupplier(): Observable<Supplier[]>{
    return this.http.get<Supplier[]>(this.url);
  }

  getSupplierById(id: number):Observable<Supplier> {
    return this.http.get<Supplier>(`${this.url}/${id}`)
  }
}
