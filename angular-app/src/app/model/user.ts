import { SectorFlatNode } from '../sector-tree/sector-tree.component';

export class User {
    id: string;
    name: string;
    sectors: SectorFlatNode[];
    agreeToTerms: boolean;

}
