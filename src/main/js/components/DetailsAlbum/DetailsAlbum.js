import React, {Component} from "react";
import './DetailsAlbum.css';

export default class DetailsAlbum extends Component {

    constructor(props) {
        super(props);
    }

    updateList = () => {
        this.props.updateAlbumList();
    }

    isInUserCollection() {
        let res = false;
        for (let i=0; i < this.props.userAlbumList.length; i++) {
            let currentAlbumId = this.props.userAlbumList[i].id_album;
            if (currentAlbumId == this.props.album.id_album) {
                res = true;
            }
        }
        return res;
    }

    addAlbumToCollection() {
        if (this.props.user_id != -1) {
            const axios = require("axios");
            console.log(this.props.album);
            axios.get("/api/addAlbumToUser?id_user=" + this.props.user_id + "&id_album=" + this.props.album.id_album).then((response) => {
                if (response.data == false) {
                    alert("Erreur sur l'ajout");
                } else {
                    this.updateList();
                }
            });
        }
    }

    removeAlbumFromCollection(inCollection) {
        if (this.props.user_id != -1) {
            const axios = require("axios");
            axios.get("/api/remAlbumFromUser?id_album=" + this.props.album.id_album + "&id_user=" + this.props.user_id).then((response) => {
                this.updateList();
                if (this.props.inCollection) {
                    this.masquerDetails();
                }
            })
        }
    }

    masquerDetails() {
        this.props.masquerDetails();
    }

    render() {
        return (
            <div class="divDetailsAlbum">
                <img src={this.props.album.img}/>
                <div id="divInfosAlbum">
                    <h1>{this.props.album.title}</h1>
                    <p>Série : {this.props.album.serie}</p>
                    <p>Numéro de série : {this.props.album.num_serie}</p>
                    <p>ISBN : {this.props.album.isbn}</p>
                    {
                        this.props.user_id != -1 ?
                            this.isInUserCollection() ?
                                <button onClick = {() => this.removeAlbumFromCollection()}>Retirer de ma collection</button>
                                :
                                <button onClick = {() => this.addAlbumToCollection()}>Ajouter à ma collection</button>
                            :
                            null
                    }
                    <br/>
                </div>
            </div>
        );
    }

}
