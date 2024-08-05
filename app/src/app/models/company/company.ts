import { UfEnum } from "../enums/uf.enum";
import { Supplier } from "../supplier/supplier";

export class Company {
    id: number;
    fantasyName: string;
    documentCnpj: string;
    uf: UfEnum;
    suppliers: Supplier[];
  
    constructor(
      id: number,
      fantasyName: string,
      documentCnpj: string,
      uf: UfEnum,
      suppliers: Supplier[]
    ) {
      this.id = id;
      this.fantasyName = fantasyName;
      this.documentCnpj = documentCnpj;
      this.uf = uf;
      this.suppliers = suppliers;
    }
  }