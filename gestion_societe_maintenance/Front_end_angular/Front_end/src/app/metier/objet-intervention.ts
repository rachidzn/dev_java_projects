
import { Materiel } from "./objet-materiel";
import { Intervenant } from "./objet-intervenant";



export class Intervention{




    public constructor(public id :number,
                        public datePrevu: string,
                        public dateEffectue: string,
                        public statut: string,
                        public commentaire: string,
                        public materiel: Materiel,
                        public intervenant: Intervenant,
                        public title :string,
                            public start: string,
                            public end: string,
                            public color: string,
                    )
                         
                       
                         {}
    

    public copyFrom(other: Intervention){
        this.id=other.id;
        this.datePrevu=other.datePrevu;
        this.dateEffectue=other.dateEffectue;
        this.statut=other.statut;
        this.commentaire=other.commentaire;
        this.materiel=other.materiel;
        this.intervenant=other.intervenant;
        this.title=other.title;
        this.start=other.start;
        this.end=other.end;
        this.color=other.color;
    }
    public toJson(){
        return {
            id:this.id,
            datePrevu:this.datePrevu,
            dateEffectue: this.dateEffectue,
            statut: this.statut,
            commentaire:this.commentaire,
            materiel: this.materiel,
            intervenant: this.intervenant,
            title:this.title,
            start:this.start,
            end:this.end,
            color:this.color,
        };
    }

    public toEvent(){
        return {
            title:this.title,
            start:this.start,
            end: this.end
        };
    }


}