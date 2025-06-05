from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtCore import *
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *

class QCustomQWidget (QtWidgets.QWidget):
    def __init__ (self):
        super(QCustomQWidget, self).__init__()
        self.textQVBoxLayout = QtWidgets.QVBoxLayout()
        self.textUpQLabel    = QtWidgets.QLabel()
        self.textDownQLabel  = QtWidgets.QLabel()
        self.textQVBoxLayout.addWidget(self.textUpQLabel)
        self.textQVBoxLayout.addWidget(self.textDownQLabel)
        self.allQHBoxLayout  = QtWidgets.QHBoxLayout()
        self.iconQLabel      = QtWidgets.QLabel()
        self.allQHBoxLayout.addWidget(self.iconQLabel, 0)
        self.allQHBoxLayout.addLayout(self.textQVBoxLayout, 1)
        self.setLayout(self.allQHBoxLayout)
        # setStyleSheet
        self.textUpQLabel.setStyleSheet('''
            color: rgb(0, 0, 255);
        ''')
        self.textDownQLabel.setStyleSheet('''
            color: rgb(255, 0, 0);
        ''')
       

    def setTextUp (self, text):
        self.textUpQLabel.setText(text)

    def setTextDown (self, text):
        self.textDownQLabel.setText(text)

    def setIcon (self, imagePath):
        self.iconQLabel.setPixmap(QtGui.QPixmap(imagePath))

class Ui_main(object):

    def goto_peoples(self):
        self.stackedview.setCurrentIndex(2)

    def goto_chats(self):
        self.stackedview.setCurrentIndex(0)

    def goto_conversations(self):
        self.stackedview.setCurrentIndex(1)
    
    def setupUi(self, main):

        #VARIABLES     
        WINDOW_POSITION_X = QApplication.desktop().screenGeometry().width() - 450
        WINDOW_POSITION_Y = QApplication.desktop().screenGeometry().height() - 493



        main.setObjectName("main")
        main.resize(365, 493)
        main.setWindowFlags(Qt.FramelessWindowHint)
        main.setAttribute(Qt.WA_TranslucentBackground, True)

        #TRANSITION IN ANIMATION
        main.transition_in = QPropertyAnimation(main, b'geometry')
        main.transition_in.setDuration(2000)
        main.transition_in.setStartValue(QRect(WINDOW_POSITION_X, WINDOW_POSITION_Y + 493 , 365, 493))
        main.transition_in.setEndValue(QRect(WINDOW_POSITION_X, WINDOW_POSITION_Y, 365, 493))
        main.transition_in.start()

        self.centralwidget = QtWidgets.QWidget(main)
        self.centralwidget.setObjectName("centralwidget")

        self.profilepicture = QtWidgets.QLabel(self.centralwidget)
        self.profilepicture.setGeometry(QtCore.QRect(10, 10, 65, 65))
        self.profilepicture.setStyleSheet("border: 2px solid white;border-radius: 32px; border-image : url(resources/profile_pic.jpeg);background-repeat: no-repeat;")
        self.profilepicture.setText("")
        self.profilepicture.setObjectName("profilepicture")

        self.gray_bg = QtWidgets.QFrame(self.centralwidget)
        self.gray_bg.setGeometry(QtCore.QRect(70, 60, 271, 441))
        self.gray_bg.setStyleSheet(";\n"
"background-color: rgb(197, 197, 197);\n"
"border-top-left-radius: 20px;\n"
"border-top-right-radius: 20px;")
        self.gray_bg.setFrameShape(QtWidgets.QFrame.StyledPanel)
        self.gray_bg.setFrameShadow(QtWidgets.QFrame.Raised)
        self.gray_bg.setObjectName("gray_bg")

        self.stackedview = QtWidgets.QStackedWidget(self.gray_bg)
        self.stackedview.setGeometry(QtCore.QRect(0, 50, 271, 391))
        self.stackedview.setStyleSheet("background-color: rgb(255, 255, 255);")
        self.stackedview.setObjectName("stackedview")

        self.chatview = QtWidgets.QWidget()
        self.chatview.setObjectName("chatview")

        self.chats_listview = QtWidgets.QListWidget(self.chatview)
        self.chats_listview.setGeometry(QtCore.QRect(10, 30, 251, 361))
        self.chats_listview.setStyleSheet("")
        self.chats_listview.setTabKeyNavigation(False)
        self.chats_listview.setProperty("showDropIndicator", False)
        self.chats_listview.setObjectName("chats_listview")

        for index, name, icon in [
            ('No.1', 'Meyoko',  'icon.png'),
            ('No.2', 'Nyaruko', 'icon.png'),
            ('No.3', 'Louise',  'icon.png')]:
            # Create QCustomQWidget
            myQCustomQWidget = QCustomQWidget()
            myQCustomQWidget.setTextUp(index)
            myQCustomQWidget.setTextDown(name)
            myQCustomQWidget.setIcon(icon)
            # Create QListWidgetItem
            myQListWidgetItem = QtWidgets.QListWidgetItem(self.chats_listview)
            # Set size hint
            myQListWidgetItem.setSizeHint(myQCustomQWidget.sizeHint())
            # Add QListWidgetItem into QListWidget
            self.chats_listview.addItem(myQListWidgetItem)
            self.chats_listview.setItemWidget(myQListWidgetItem, myQCustomQWidget)

        self.chat_label = QtWidgets.QLabel(self.chatview)
        self.chat_label.setGeometry(QtCore.QRect(30, 0, 41, 31))
        self.chat_label.setObjectName("chat_label")

        self.stackedview.addWidget(self.chatview)

        self.conversationview = QtWidgets.QWidget()
        self.conversationview.setObjectName("conversationview")

        self.header_box = QtWidgets.QFrame(self.conversationview)
        self.header_box.setGeometry(QtCore.QRect(0, 0, 271, 41))
        self.header_box.setFrameShape(QtWidgets.QFrame.StyledPanel)
        self.header_box.setFrameShadow(QtWidgets.QFrame.Raised)
        self.header_box.setObjectName("header_box")

        self.person_name = QtWidgets.QLabel(self.header_box)
        self.person_name.setGeometry(QtCore.QRect(78, 0, 121, 41))
        self.person_name.setAlignment(QtCore.Qt.AlignCenter)
        self.person_name.setObjectName("person_name")

        self.person_profilepic = QtWidgets.QLabel(self.header_box)
        self.person_profilepic.setGeometry(QtCore.QRect(20, 10, 21, 21))
        self.person_profilepic.setStyleSheet("border-radius: 10px;\n"
"background-color: rgb(0, 255, 255);")
        self.person_profilepic.setText("")
        self.person_profilepic.setObjectName("person_profilepic")

        self.hline = QtWidgets.QFrame(self.conversationview)
        self.hline.setGeometry(QtCore.QRect(15, 40, 240, 1))
        self.hline.setStyleSheet("background-color: rgb(167, 167, 167);")
        self.hline.setFrameShape(QtWidgets.QFrame.HLine)
        self.hline.setFrameShadow(QtWidgets.QFrame.Sunken)
        self.hline.setObjectName("hline")

        self.converstaion_box = QtWidgets.QListView(self.conversationview)
        self.converstaion_box.setGeometry(QtCore.QRect(0, 41, 271, 291))
        self.converstaion_box.setObjectName("converstaion_box")

        self.chat_inputbox = QtWidgets.QLineEdit(self.conversationview)
        self.chat_inputbox.setGeometry(QtCore.QRect(20, 345, 191, 31))
        self.chat_inputbox.setStyleSheet("border: 2px solid rgb(184, 184, 184);\n"
"border-radius: 10px;")
        self.chat_inputbox.setObjectName("chat_inputbox")

        self.send_btn = QtWidgets.QPushButton(self.conversationview)
        self.send_btn.setGeometry(QtCore.QRect(220, 346, 31, 31))
        self.send_btn.setStyleSheet("background-color: rgb(6, 176, 255);")
        self.send_btn.setObjectName("send_btn")

        self.hline_2 = QtWidgets.QFrame(self.conversationview)
        self.hline_2.setGeometry(QtCore.QRect(15, 340, 240, 1))
        self.hline_2.setStyleSheet("background-color: rgb(167, 167, 167);")
        self.hline_2.setFrameShape(QtWidgets.QFrame.HLine)
        self.hline_2.setFrameShadow(QtWidgets.QFrame.Sunken)
        self.hline_2.setObjectName("hline_2")

        self.stackedview.addWidget(self.conversationview)

        self.peopleview = QtWidgets.QWidget()
        self.peopleview.setObjectName("peopleview")

        self.peoples_listview = QtWidgets.QListView(self.peopleview)
        self.peoples_listview.setGeometry(QtCore.QRect(10, 30, 251, 361))
        self.peoples_listview.setStyleSheet("background-color: rgb(208, 255, 0);")
        self.peoples_listview.setObjectName("peoples_listview")

        self.peoples_label = QtWidgets.QLabel(self.peopleview)
        self.peoples_label.setGeometry(QtCore.QRect(20, 0, 61, 31))
        self.peoples_label.setTextFormat(QtCore.Qt.AutoText)
        self.peoples_label.setAlignment(QtCore.Qt.AlignCenter)
        self.peoples_label.setObjectName("peoples_label")

        self.stackedview.addWidget(self.peopleview)

        self.search_box = QtWidgets.QFrame(self.gray_bg)
        self.search_box.setGeometry(QtCore.QRect(40, 20, 191, 31))
        self.search_box.setStyleSheet("background-color: rgb(255, 255, 255);\n"
"border-top-left-radius: 15px;\n"
"border-top-right-radius: 15px;")
        self.search_box.setFrameShape(QtWidgets.QFrame.StyledPanel)
        self.search_box.setFrameShadow(QtWidgets.QFrame.Raised)
        self.search_box.setObjectName("search_box")

        self.search_editbox = QtWidgets.QLineEdit(self.search_box)
        self.search_editbox.setGeometry(QtCore.QRect(58, 0, 81, 31))
        self.search_editbox.setAlignment(QtCore.Qt.AlignCenter)
        self.search_editbox.setObjectName("search_editbox")

        self.search_btn = QtWidgets.QPushButton(self.gray_bg)
        self.search_btn.setGeometry(QtCore.QRect(240, 30, 15, 15))
        self.search_btn.setStyleSheet("background-color: rgb(255, 255, 255);")
        self.search_btn.setText("")
        self.search_btn.setObjectName("search_btn")

        self.close = QtWidgets.QPushButton(self.centralwidget)
        self.close.setGeometry(QtCore.QRect(350, 70, 12, 12))
        self.close.setStyleSheet("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:1, stop:0 rgba(255, 0, 0, 255), stop:0.477273 rgba(255, 43, 43, 255), stop:1 rgba(255, 255, 255, 255));\n"
"border: none;\n"
"border-radius: 5px;")
        self.close.setText("")
        self.close.setFlat(False)
        self.close.setObjectName("close")

        self.maximize = QtWidgets.QPushButton(self.centralwidget)
        self.maximize.setGeometry(QtCore.QRect(350, 100, 12, 12))
        self.maximize.setStyleSheet("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:1, stop:0 rgba(0, 255, 0, 255), stop:0.579545 rgba(113, 255, 113, 255), stop:1 rgba(255, 255, 255, 255));\n"
"border: none;\n"
"border-radius: 5px;")
        self.maximize.setText("")
        self.maximize.setFlat(False)
        self.maximize.setObjectName("maximize")

        self.minimize = QtWidgets.QPushButton(self.centralwidget)
        self.minimize.setGeometry(QtCore.QRect(350, 130, 12, 12))
        self.minimize.setStyleSheet("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:1, stop:0 rgba(0, 36, 255, 255), stop:0.505682 rgba(88, 112, 255, 255), stop:1 rgba(255, 255, 255, 255));\n"
"border: none;\n"
"border-radius: 5px;")
        self.minimize.setText("")
        self.minimize.setFlat(False)
        self.minimize.setObjectName("minimize")

        self.view_frame = QtWidgets.QFrame(self.centralwidget)
        self.view_frame.setGeometry(QtCore.QRect(180, 20, 161, 31))
        self.view_frame.setStyleSheet("background-color: rgb(255, 255, 255);\n"
"border-radius:15px;")
        self.view_frame.setFrameShape(QtWidgets.QFrame.StyledPanel)
        self.view_frame.setFrameShadow(QtWidgets.QFrame.Raised)
        self.view_frame.setObjectName("view_frame")

        self.chatview_btn = QtWidgets.QPushButton(self.view_frame)
        self.chatview_btn.setGeometry(QtCore.QRect(0, 0, 81, 31))
        self.chatview_btn.clicked.connect(self.goto_chats)
        self.chatview_btn.setObjectName("chatview_btn")

        self.peopleview_btn = QtWidgets.QPushButton(self.view_frame)
        self.peopleview_btn.setGeometry(QtCore.QRect(80, 0, 81, 31))
        self.peopleview_btn.clicked.connect(self.goto_peoples)
        self.peopleview_btn.setObjectName("peopleview_btn")

        self.vertical_line = QtWidgets.QFrame(self.view_frame)
        self.vertical_line.setGeometry(QtCore.QRect(80, 6, 1, 20))
        self.vertical_line.setStyleSheet("background-color: rgb(197, 197, 197);")
        self.vertical_line.setFrameShape(QtWidgets.QFrame.VLine)
        self.vertical_line.setFrameShadow(QtWidgets.QFrame.Sunken)
        self.vertical_line.setObjectName("vertical_line")
        main.setCentralWidget(self.centralwidget)

        self.retranslateUi(main)
        self.stackedview.setCurrentIndex(0)
        QtCore.QMetaObject.connectSlotsByName(main)

    def retranslateUi(self, main):
        _translate = QtCore.QCoreApplication.translate
        main.setWindowTitle(_translate("main", "Chatty"))
        self.chat_label.setText(_translate("main", "CHATS"))
        self.person_name.setText(_translate("main", "PERSON NAME"))
        self.send_btn.setText(_translate("main", "SEND"))
        self.peoples_label.setText(_translate("main", "PEOPLES"))
        self.search_editbox.setPlaceholderText(_translate("main", "S E A R C H"))
        self.chatview_btn.setText(_translate("main", "CHAT"))
        self.peopleview_btn.setText(_translate("main", "PEOPLE"))


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    main = QtWidgets.QMainWindow()
    ui = Ui_main()
    ui.setupUi(main)
    main.show()
    sys.exit(app.exec_())
