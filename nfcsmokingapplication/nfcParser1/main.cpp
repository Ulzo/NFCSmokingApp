#include <cstdlib>
#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

/*
parseAnswerList takes in a string of answers "Answer1,Answer2,Answer3,...,AnswerN" 
and returns a string vector of the separated answers 
[Answer1,Answer2,Answer3,...,AnswerN].
*/
vector<string> parseAnswerList(string answers)
{
    //int commaIndex = 0;
    //int counter = 0;
    
    //int start = 0;
    //int end = answers.size()-1;
    
    string ansSub = "";
      
    vector<string> list;
   
    //vector<int> commaList;
    
    int firstComma = -1;
    int secondComma = -2;
    int temp = -3;
    
    for(int k = 0; k < answers.size(); k++)
    {
       if(answers[k] == ',')
       {
          // gets the very first Answer string
          if(firstComma < 0 && secondComma < 0)
          {
             firstComma = k;
             secondComma = k;
             ansSub = answers.substr(0,k);
          }
          // gets the second Answer string(when # of Answers > 2)
          else if(firstComma >= 0 && secondComma == firstComma)
          {
             secondComma = k;
             ansSub = answers.substr(firstComma+1,secondComma-firstComma-1);
          }
          else
          {
             temp = secondComma;
             secondComma = k;
             firstComma = temp;   
             ansSub = answers.substr(firstComma+1,secondComma-firstComma-1); 
          }
          
          list.push_back(ansSub);     // add Answer string to list                              
       }
       
       // need to include final Answer option after last comma
       if(k == answers.size() - 1)
       {
          ansSub = answers.substr(secondComma+1,answers.size()-secondComma-1);  
          list.push_back(ansSub);                
       }           
                                   
    }
       
    return list;            
    
}  // end of parseAnswerList()


int main(int argc, char *argv[])
{    
    ifstream qFile;
    
    string line;
    
    string qType = "", question = "", answerList = "";

    vector<string> answers;
    
    int questionCounter = 0;
    
    cout << "Now opening question file.\n\n";
    
    // future implementations can have file name as command line arg
    qFile.open("testQuestions2.txt");
    
    if (qFile.is_open())
    {
       while(qFile.good() )
       {
           getline(qFile,line);
           
           if(line[0] == '[')  // skip blank lines
           {
           //cout << line << endl;
           
                if(line == "[Slider]")
                {
                     qType = "Slider";
                     // stores the next line into question
                     getline(qFile,question);
                     question = question.substr(1,question.size()-2);
                     // stores the next-next line into answerList
                     getline(qFile,answerList);
                     // remove brackets from answerList
                     answerList = answerList.substr(1,answerList.size()-2);
                     answers = parseAnswerList(answerList); 
                }
                else if(line == "[Button]")
                {
                     qType = "Button";
                     getline(qFile,question); 
                     question = question.substr(1,question.size()-2);
                     getline(qFile,answerList);
                     answerList = answerList.substr(1,answerList.size()-2); 
                     answers = parseAnswerList(answerList); 
                }
                else if(line == "[Check]")
                {
                     qType = "Check";
                     getline(qFile,question);
                     question = question.substr(1,question.size()-2); 
                     getline(qFile,answerList);
                     answerList = answerList.substr(1,answerList.size()-2); 
                     answers = parseAnswerList(answerList);             
                }
                        
           questionCounter++;
           
           // print parsed question info to screen
           cout << questionCounter << ". ";             
           cout << "What kind of question is it? " 
                << qType << endl;
           cout << "Question is: " << question << endl;
           cout << "The answer options are: \n";
                  
           for(int i = 0; i < answers.size(); i++)
                   cout << answers[i] << endl;
           cout << endl;    
           }           
       }          
    }
    else
    {
        cout << "Error! file did not open!\n";    
    }
    
    qFile.close();
    cout << "File has been closed.\n";
    
    // note these last 2 lines are for Dev C++
    // to delay the console from closing until keyboard input is received
    // Linux will say "PAUSE: command not found" but program will still run
    // correctly
    system("PAUSE");
    return EXIT_SUCCESS;
}

       
