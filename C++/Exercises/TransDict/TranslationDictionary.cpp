/************* TranslationDictionary.cpp **********************/
#include <string>
#include <iostream>
#include <fstream>

#include "TranslationDictionary.h"

using namespace std;

/*************************************************************************/
/* builds dictionary from file with translation pairs source/translation */
/* on single lines                                                       */
/*************************************************************************/

Trans_Dict::Trans_Dict(string file_name) {
    string line, translation, word;
    ifstream f;
    Transpair p;
    size_t trans_offset;

    f.open(file_name);
    if(f) {
        opened_ok = true;
    }
    else {
        opened_ok = false;
    }

    if(opened_ok) {

        while(f >> line) {
            trans_offset = line.find('/'); /* trans_offset is location of / */

            if(trans_offset == string::npos) {
                cout << "there was an entry with no /\n";
                p.french = line;
                p.english = "";
                thewords.push_back(p);
                continue;
            }
            else {
                word = line.substr(0,trans_offset); // up to /
                translation = line.substr(trans_offset+1); // after /
                // make transpair with word and translation
                p.french = word;
                p.english = translation;
                thewords.push_back(p); // add transpair to thewords
            }

        }
    }


}


/*****************************************************************/
/* looks up word in dictionary returning translation or notfound */
/*****************************************************************/
bool Trans_Dict::lookup(string word, string& trans) {
    int i;
    for(i = 0; i < thewords.size(); i++) {
        if (thewords[i].french == word) {
            trans = thewords[i].english;
            return true;
        }
    }

    return false;
}

bool Trans_Dict::engLookup(string eng, string &trans) {
    for (int i = 0; i < thewords.size(); i++) {
        if (thewords[i].english == eng) {
            trans = thewords[i].french;
            return true;
        }
    }
    return false;
}

void Trans_Dict::update(string french, string english) {
    bool found = false;
    int i = 0;
    while (i < thewords.size() && !found) {
        if (thewords[i].french == french) {
            string oldEng = thewords[i].english;
            thewords[i].english = english;
            cout << "English trans of " << french << " updated from " << oldEng << " to " << english << endl;
            found = true;
        } else {
            i++;
        }
    }
    if (!found) {
        Transpair newPair;
        newPair.english = english;
        newPair.french = french;
        thewords.push_back(newPair);
        cout << "New trans pair added: " << "Fr - " << french << " | En - " << english << endl;
    }
}

void Trans_Dict::export_to_file(string filename) {
    ofstream out_stream;
    out_stream.open(filename, ios::out);

    int i = 0;
    while (i < thewords.size()) {
        out_stream << thewords[i].french << "/" << thewords[i].english << endl;
        i++;
    }

    out_stream.close();
}

//print_dict member method, used to print the dictionary to the console so it can be compared to the exported file.
void Trans_Dict::print_dict() {
    for (int i = 0; i < thewords.size(); i++) {
        cout << thewords[i].french << "/" << thewords[i].english << endl;
    }
    cout << "----------" << endl;
}