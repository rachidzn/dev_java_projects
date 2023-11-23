import { Intervenant }from "./objet-intervenant";

export class Groupe{
    public constructor(public id :number,
                        public nom: string,
                        )
                         
                       
                         {}
    

    public copyFrom(other: Groupe){
        this.id=other.id;
        this.nom=other.nom;
        

    }

 
    

    public toJson(){
        return {
            id:this.id,
            nom:this.nom,
         
            
        };
    }

}