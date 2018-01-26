var Questions = function ( question, answers, correctanswer){
	
	this.question = question;
	this.answers = answers;
	this.correctanswer = correctanswer;
};

Questions.prototype.checkAnswer = function (ans){

	if (parseInt(ans) === this.correctanswer){
		console.log("Correct Answer!");
		return 1;
	}
	else{
		console.log("Wrong Answer!");
		return 0;
	}


}

Questions.prototype.print = function(){
	
	console.log(this.question);
	for(var i=0;i<this.answers.length;i++)
		console.log(this.answers[i]);
}

var game = function(quesArray){

	
	var random = Math.floor((Math.random() * quesArray.length) );
	//console.log(random);
	quesArray[random].print();


	var a = prompt('Please select the correct answer from the console');
	if(a==='exit')
		return a;
	else{
		var x = quesArray[random].checkAnswer(a);
		return x;

	}
	

}

var ques1 = new Questions("What is the current weather?", ['1.winter','2.summer', '3.spring','4.fall'], 1);
var ques2 = new Questions("What is the color of the sun?",['1.green','2.yellow'],2);
var ques3 = new Questions("Poems are good?",['1.Yes', '2.No'],1);

var qArray = new Array(ques1,ques2, ques3);
var score = 0;
while(true){


	var play = game(qArray)
	
	if(play ==='exit')
		break;
	else if ( play === 1)
		score++;
}

console.log(score);
