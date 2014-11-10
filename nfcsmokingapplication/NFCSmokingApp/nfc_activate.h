#ifndef NFC_ACTIVATE_H
#define NFC_ACTIVATE_H

#include <QMainWindow>

namespace Ui {
class NFC_Activate;
}

class NFC_Activate : public QMainWindow
{
    Q_OBJECT
    
public:
    explicit NFC_Activate(QWidget *parent = 0);
    ~NFC_Activate();
    
private slots:
    void on_pushButton_clicked();

private:
    Ui::NFC_Activate *ui;
};

#endif // NFC_ACTIVATE_H
