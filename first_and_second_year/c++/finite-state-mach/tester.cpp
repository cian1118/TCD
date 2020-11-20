#include "FSA.h"
#include <iostream>
#include <string>

using namespace std;

int main() {

    FSA fsa;
    fsa.sep = 0;
    fsa.update("me");
    fsa.update("my");
//    fsa.update("do");
//    fsa.update("day");
    fsa.show();

    if (fsa.accept("me")) {
      cout << "accepts me" << endl;
    }
    else {
      cout << "rejects me" << endl;
    }

    if (fsa.accept("dy")) {
      cout << "accepts dy" << endl;
    }
    else {
      cout << "rejects dy" << endl;
    }

    fsa.sep = ' ';
    if (fsa.accept("m y")) {
      cout << "accepts m y" << endl;
    }
    else {
      cout << "rejects m y"<< endl;
    }

    return 0;
}
