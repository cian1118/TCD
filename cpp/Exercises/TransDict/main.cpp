/*********************************/
/* tests the Trans_Dict class    */
/*********************************/
#include <iostream>
#include "TranslationDictionary.h"
#include <stdlib.h>

using namespace std;

void tester_function(Trans_Dict& d, string name, vector<string> fr_words);

int main() {
    Trans_Dict d1("translist");
    Trans_Dict d2("translist2");

    if(!d1.opened_ok) {
        cerr << "dictionary to translist did not open ok\n";
        exit(0);
    }

    if(!d2.opened_ok) {
        cerr << "dictionary to translist2 did not open ok\n";
        exit(0);
    }

    string trans_english;

    if(d1.lookup("maison",trans_english)) {
        cout << "translist trans of maison is " << trans_english << '\n';
    }
    else {
        cout << "no translist trans of maison\n";
    }

    if(d1.lookup("chien",trans_english)) {
        cout << "translist trans of chien is " << trans_english << '\n';
    }
    else {
        cout << "no translist trans of chien\n";
    }

    if(d1.lookup("garcon",trans_english)) {
        cout << "translist trans of garcon is " << trans_english << '\n';
    }
    else {
        cout << "no translist trans of garcon\n";
    }


    if(d2.lookup("garcon",trans_english)) {
        cout << "translist2 trans of garcon is " << trans_english << '\n';
    }
    else {
        cout << "no translist2 trans of garcon\n";
    }


    if(d2.lookup("sante",trans_english)) {
        cout << "translist2 trans of sante is " << trans_english << '\n';
    }
    else {
        cout << "no translist2 trans of sante\n";
    }

    if(d2.lookup("maison",trans_english)) {
        cout << "translist2 trans of maison is " << trans_english << '\n';
    }
    else {
        cout << "no translist2 trans of maison\n";
    }

    /* you could replace the above series of tests with the following
       more economical code which use a function to run the tests

     */
    // vector<string> fr_words;
    // fr_words.push_back("maison");
    // fr_words.push_back("chien");
    // fr_words.push_back("garcon");
    // tester_function(d1, "translist", fr_words);

    // fr_words.clear();
    // fr_words.push_back("garcon");
    // fr_words.push_back("sante");
    // fr_words.push_back("maison");
    // tester_function(d2, "translist2", fr_words);


    vector<string> en_words;

    en_words.push_back("house");
    en_words.push_back("dog");
    en_words.push_back("boy");
    tester_function(d1, "translist", en_words);


    en_words.clear();
    en_words.push_back("boy");
    en_words.push_back("health");
    en_words.push_back("house");
    tester_function(d2, "translist2", en_words);

    cout << endl;

    d1.update("bleu", "blue");
    d1.update("chien", "doggy");
    d1.update("chien", "dog");

    d1.export_to_file("exported_dict");
    d2.export_to_file("export_dict2");

    cout << endl << "compare to thewords vector of each dict:" << endl;
    d1.print_dict();
    d2.print_dict();

}

//set dictionary.engLookup or ".lookup for English or French as the base lang, respectively.
void tester_function(Trans_Dict& dictionary, string name, vector<string> words) {

    string translation;
    bool found;
    for(unsigned int i=0; i < words.size(); i++) {
        if(dictionary.engLookup(words[i], translation)) {
            cout << name << " trans of " << words[i] << " is " << translation << endl;
        }
        else {
            cout << "no " << name << " trans of " << words[i] << endl;
        }
    }
}