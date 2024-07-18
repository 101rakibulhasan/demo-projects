const {app,BrowserWindow,Tray, Menu, ipcMain, globalShortcut} = require('electron')
const path = require('path');
const electron = require('electron');
const data = require('./data')

var WIDTH = 320;
var HEIGHT = 580;
var OFFSIDE_X = 40;
var OFFSIDE_Y = 20;

ipcMain.on("msg",(event,data)=>{
    console.warn(data)
})

function createWindows()
{
    const win = new BrowserWindow({
        width:WIDTH,
        height:HEIGHT,
        x: electron.screen.getPrimaryDisplay().size.width - WIDTH - OFFSIDE_X,
        y: electron.screen.getPrimaryDisplay().size.height - HEIGHT - OFFSIDE_Y,
        frame: false,
        transparent: true,
        //backgroundColor: "#ff0000",
        alwaysOnTop:true,
        title: "Awesoem",
        autoHideMenuBar: true,
        minimizable:true,
        icon : "img/icon.png",
        webPreferences:{
            nodeIntegration:true,
            contextIsolation: false,
            preload : path.join(__dirname, "index.js"),
        },
        

    })

    win.loadFile("index.html");

    //TRAY
    tray = new Tray('img/icon.png')
    tray.setToolTip('Bubble is currently running...')
    tray.on("click",()=>{
        if(!win.isVisible())
        {
            win.show()
        }
    })

    //TRAY MENU
    let template = [{label:'Exit'}]
    let contextMenu = Menu.buildFromTemplate(template)
    tray.setContextMenu(contextMenu)

    globalShortcut.register("CommandOrControl+Super+X",()=>{
        win.show()
    })

    ipcMain.on('minimize', (event,data) => {
        win.minimize();
      })

}

app.whenReady().then(createWindows)