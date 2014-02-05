/**
* @author Andrew Gulla (100395486)
* @section DESCRIPTION
* @version 1.0
* @file DivideAndConquer.cpp
* This Program searches for a
* substring within a string.
*/

#include <iostream>
#include <string>

using namespace std;

string mString;
string sString;

void answer(bool x) {
	if ( x == true )
		cout << "The requested sub-string does exist in the string. \n" << endl;
	else 
		cout << "Sorry. Your sub-string does not exist in the string. \n" << endl;
}

bool search(string, string);

int main(){

		cout << "CSCI 3070: Assignment 1" << endl << "Andrew Gulla (100395486) \n" << endl;
		
		while(mString.empty()) {
			cout << "Enter in a string that will be used as the main string: \n" << endl;
			getline (cin, mString);
		}

		cout << "The following is the string that was entered: \n" << endl;
		cout << mString << endl;
	
		while(sString.empty()) {
			cout << "Please enter in the string you would like to search for: " << endl;
			getline (cin, sString);
		}

		if (mString.length() < sString.length()) {
			answer(false);
		}
		else {
			answer(search(mString, sString));
		}

		return 0;
}

bool search( string x, string target) {

	int mid = x.length() / 2;
	string leftSide = x.substr( 0, mid);
	string rightSide = x.substr(mid);

	 if ((x.find(target)) < mid && ((x.find(target)+target.length())> mid)){
		
		 if((mid-x.find(target)) < ((x.find(target) + target.length() - mid))){
			
			 rightSide = x.substr(x.find(target));
		}
		
		 else
			leftSide = x.substr(0, x.find(target) + target.length());
	 }

	 if (!target.compare(leftSide) || !target.compare(rightSide)) {
		 
		 return true;
	 }

	 else if (leftSide.find(target) != string::npos) {
		 
		 return search(leftSide, target);
	 }

	 else if (rightSide.find(target) != string::npos) {
		 
		 return search(rightSide, target);
	 }
	
	 else {
		 
		 return false;
	 }

}