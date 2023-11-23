import { Article } from "./objet-article";
import { Site } from "./objet-site";
import { Client } from "./objet-client";

export class Materiel{
    public constructor(public id :number,
                        public serialID : String,
                        public lesite : Site,
                         public client: Client,
                         public article: Article)
                       
                         {}
    

    public copyFrom(other: Materiel){
        this.id=other.id;
        this.serialID=other.serialID;
        this.lesite=other.lesite;
        this.client=other.client;
        this.article=other.article;
       
    }
    public toJson(){
        return {
            id:this.id,
            serialID:this.serialID,
            lesite:this.lesite,
            client:this.client,
            article:this.article
        }
    }
}

