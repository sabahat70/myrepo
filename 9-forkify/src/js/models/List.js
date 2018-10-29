import uniqId from 'uniqid';

export default class List {

	constructor(){
		this.items = [];
	}

	addItem(count,unit, ingredient) {
		const item ={
			id:uniqId(),
			count,
			unit,
			ingredient
		}
		this.items.push(item);
		return item;
	}

	deleteItem(id){
		const index = this.items.findIndex(el => el.id === id);
		//splice returns the required value from start index to the count and mutates original array
		this.items.splice(index, 1);
	}

	updateCount(id, newCount){

		this.items.find(el => el.id === id).count = newCount;
	}
}