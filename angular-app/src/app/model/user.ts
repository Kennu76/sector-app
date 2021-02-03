import { TreeModel } from 'angular-tree-component';
import { SectorFlatNode } from '../sector-tree/sector-tree.component';
import { Sector } from './sector';

export class User {
    id: string;
    name: string;
    sectors: SectorFlatNode[];
    agreeToTerms: boolean;

}
