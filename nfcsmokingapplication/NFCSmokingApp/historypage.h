#ifndef HISTORYPAGE_H
#define HISTORYPAGE_H

#include <QMainWindow>

namespace Ui {
class HistoryPage;
}

class HistoryPage : public QMainWindow
{
    Q_OBJECT
    
public:
    explicit HistoryPage(QWidget *parent = 0);
    ~HistoryPage();
    void history_btn_pressed();
    
private:
    Ui::HistoryPage *ui;
};

#endif // HISTORYPAGE_H
