#include "historypage.h"
#include "ui_historypage.h"

HistoryPage::HistoryPage(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::HistoryPage)
{
    ui->setupUi(this);
}

HistoryPage::~HistoryPage()
{
    delete ui;
}
