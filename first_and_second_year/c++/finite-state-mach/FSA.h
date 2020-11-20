#if !defined(FSA_h)
#define FSA_h
#include "Node.h"

class FSA {
public:
    FSA();
    char sep;

    /******************/
    /* ACCEPT METHODS */
    /******************/

    /* turns input into a vector key using sep as separator */
    bool accept(string input);

    /******************/
    /* UPDATE METHODS */
    /******************/

    void update(string input);

    /******************/
    /* SHOW METHODS   */
    /******************/

    void show();
//    void dot_show();

private:
    vector<NodePtr> initial_states;
    vector<string> key;
    void set_key_from_string(string in);

    bool accept();

};

#endif