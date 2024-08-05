import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Company } from '../../models/company/company';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) { }

  private url: string = 'http://localhost:8080/company';

  createCompany(company: Company): Observable<Company> {
    return this.http.post<Company>(this.url, company);
  }

  listAllCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>(this.url);
  }

  selectCompanyById(id: number): Observable<Company> {
    const url = `${this.url}/${id}`;
    return this.http.get<Company>(url);
  }

  updateCliente(company: Company): Observable<Company> {
    return this.http.put<Company>(this.url, company);
  }

  deleteCompany(id: number): Observable<Company> {
    const url = `${this.url}/${id}`;
    return this.http.delete<Company>(url);
  }

  getCompany(): Observable<Company[]>{
    return this.http.get<Company[]>(this.url);
  }

  getCompanyById(id: number):Observable<Company> {
    return this.http.get<Company>(`${this.url}/${id}`)
  }
}
