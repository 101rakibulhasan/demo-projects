const {ipcRenderer} = require("electron");
function app_minimize()
{
    ipcRenderer.send("minimize", "min");
}

function app_close()
{
    window.close();
}

