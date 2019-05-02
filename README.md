# stock-blogger-project
Business Project - OOP Spring 2019


To get everything up and running properly, please follow these instructions VERY CLOSELY!

Summary of Steps:
SETUP
***(ALWAYS MAKE SURE YOU OPEN VSCODE AS AN ADMINISTRATOR OR POWERSHELL/NODE WON'T HAVE PROPER PERMISSIONS)***
1) Install necessary angular libraries in stock-blogger-project\webapp\stockblogger with Angular CLI
2) Serve the development server (ng s) or (npm start) and navigate to http://localhost:4200
3) Start up all required servers(Twitter API, Ticker API, etc.
cont.........

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.3.5.

## Get STARTED!
BEFORE YOU DO ANYTHING!!!!!! YOU MUST NAVIGATE TO THE CORRECT DIRECTORY!!!!

cd WebApp/StockBlogger

THEN PROCEED WITH REMAINING COMMANDS TO INSTALL REQUIRED ANGULAR LIBRARIES

npm install -g @angular/cli

THEN

npm install

ONCE BOTH OF THOSE INSTALLS ARE DONE IN THAT EXACT ORDER,

ng serve (or ng s)

IF THAT COMMAND DOES NOT WORK( not recognized)

npm start

IF ERRORS CONTINUE...

# ONLY USE THESE IF THE COMMAND LINE THROWS ERRORS AFTER INSTALLING THE FIRST TWO
npm install @angular/common

npm install @angular/core

npm install @angular/compiler

npm install @angular/platform-browser

npm install @angular/platform-browser-dynamic

npm install @angular/router

npm install --save-dev @angular-devkit/build-angular

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
