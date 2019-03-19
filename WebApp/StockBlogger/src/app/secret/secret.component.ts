import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-secret',
  templateUrl: './secret.component.html',
  styleUrls: ['./secret.component.css']
})
export class SecretComponent implements OnInit {
  answers = ['Rock', 'Paper', 'Scissors'];
  prompt;
  userAnswer;
  compAnswer;
  endGame = '';

  constructor() { }

  ngOnInit() {
  }

  onClick() {
    console.log('Rock Paper Scissors Started!');
    this.prompt = 'Rock, Paper, or Scissors?';
    this.compAnswer = this.answers[Math.floor(Math.random() * 3)];
    console.log('Computer: ' + this.compAnswer);

    this.getUserAnswer();
    switch (this.compAnswer) {
      case 'Rock':
      if (this.userAnswer === 'Paper') {
        this.endGame = 'Paper beats Rock! You WIN!!';
        console.log('Player wins');
      }
      if (this.userAnswer === 'Rock') {
        this.endGame = 'Two Rocks? Try again!';
        console.log('Computer wins');
      }
      if (this.userAnswer === 'Scissors') {
        this.endGame = 'Rock beats Scissors! YOU LOSE!!';
        console.log('Nobody wins');
      }
      break;
      case 'Paper':
      if (this.userAnswer === 'Paper') {
        this.endGame = 'Two Papers??? Try again!!!!';
        console.log('Nobody wins');
      }
      if (this.userAnswer === 'Rock') {
        this.endGame = 'Paper beats Rock! YOU LOSE!!!';
        console.log('Computer wins');
      }
      if (this.userAnswer === 'Scissors') {
        this.endGame = 'Scissors beats Paper! You WIN!!!';
        console.log('Player wins');
      }
      break;
      case 'Scissors':
      if (this.userAnswer === 'Paper') {
        this.endGame = 'Scissors beats Paper! YOU LOSE!!!';
        console.log('Computer wins');
      }
      if (this.userAnswer === 'Rock') {
        this.endGame = 'Rock beats Scissors! YOU WIN!!!';
        console.log('Player wins');
      }
      if (this.userAnswer === 'Scissors') {
        this.endGame = 'Two Scissors?? Try again!!';
        console.log('Nobody wins');
      }
      break;
      default:
      this.compAnswer = '';
      this.userAnswer = '';
      this.endGame = 'Click the button to get a game going!'
      // tslint:disable-next-line:quotemark
      console.log("That isn't allowed");
    }
  }

  getUserAnswer() {
    const confirm = prompt('ROCK, PAPER, OR SCISSORS???!!??');

    switch (confirm) {
      case 'rock':
      this.userAnswer = 'Rock';
      break;
      case 'Rock':
      this.userAnswer = 'Rock';
      break;
      case 'ROCK':
      this.userAnswer = 'Rock';
      break;
      case 'paper':
      this.userAnswer = 'Paper';
      break;
      case 'Paper':
      this.userAnswer = 'Paper';
      break;
      case 'PAPER':
      this.userAnswer = 'Paper';
      break;
      case 'scissors':
      this.userAnswer = 'Scissors';
      break;
      case 'Scissors':
      this.userAnswer = 'Scissors';
      break;
      case 'SCISSORS':
      this.userAnswer = 'Scissors';
      break;
    }
  }
}
