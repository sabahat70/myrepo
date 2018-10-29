import axios from 'axios';
import {key,api} from '../config';

export default class Search{

	constructor(query){
		this.query = query;
	}

	async getResults(){

		try{
			const res = await axios(`${api}search?key=${key}&q=${this.query}`);
			this.result = res.data.recipes;
			console.log(res.data.recipes);
		}catch(error){
			alert(error);
		}

	}
}
