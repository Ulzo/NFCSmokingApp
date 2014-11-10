#include "nfc_activate.h"
#include "ui_nfc_activate.h"

NFC_Activate::NFC_Activate(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::NFC_Activate)
{
    ui->setupUi(this);
}

NFC_Activate::~NFC_Activate()
{
    delete ui;
}

void NFC_Activate::on_pushButton_clicked()
{

}
