import { Company } from "../company/company";
import { TypePersonEnum } from "../enums/typeperson.enum";

export class Supplier {
    id: number;
    name: string;
    typePerson: TypePersonEnum;
    documentCnpjCpf: string;
    documentRg: string;
    birthDate: Date;
    createdAt: Date;
    phone: number;
    company: Company;
  
    constructor(
      id: number,
      name: string,
      typePerson: TypePersonEnum,
      documentCnpjCpf: string,
      documentRg: string,
      birthDate: Date,
      createdAt: Date,
      phone: number,
      company: Company
    ) {
      this.id = id;
      this.name = name;
      this.typePerson = typePerson;
      this.documentCnpjCpf = documentCnpjCpf;
      this.documentRg = documentRg;
      this.birthDate = birthDate;
      this.createdAt = createdAt;
      this.phone = phone;
      this.company = company;
    }
  }