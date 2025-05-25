Imports System.Net
Imports System.Linq
Imports System.Management

Public Class Form6

    Private Sub Form6_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        BackColor = Color.Black
        TransparencyKey = BackColor
        Me.TopMost = True
        Timer1.Enabled = True
        Timer2.Enabled = True

        Dim TotalRAM As Integer
        TotalRAM = My.Computer.Info.TotalPhysicalMemory / (1024 * 1024)
        Label15.Text = "Total Physical Memory : " & TotalRAM & " MB"

        Label13.Text = "Windows Platform : " & My.Computer.Info.OSFullName

        Dim query As New System.Management.SelectQuery("Win32_VideoController")
        Dim search As New System.Management.ManagementObjectSearcher(query)
        Dim info As System.Management.ManagementObject
        For Each info In search.Get()
            Label12.Text = "GPU Name : " & info("Caption").ToString

        Next

        Dim CPUName As String
        CPUName = My.Computer.Registry.GetValue("HKEY_LOCAL_MACHINE\HARDWARE\DESCRIPTION\SYSTEM\CentralProcessor\0", "ProcessorNameString", Nothing)
        Label8.Text = " Processor Name : " & CPUName

        Dim Freq As String
        Freq = My.Computer.Registry.GetValue("HKEY_LOCAL_MACHINE\HARDWARE\DESCRIPTION\SYSTEM\CentralProcessor\0", "~MHz", Nothing)
        Label9.Text = " Core Speed(Recently) : " & Freq & " MHz"

    End Sub

    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick

        Dim cpu As Integer = PerformanceCounter1.NextValue
        Label1.Text = "CPU : " & cpu.ToString & "%"

        Dim ram As Integer = (100 - (My.Computer.Info.AvailablePhysicalMemory / My.Computer.Info.TotalPhysicalMemory) * 100)
        Label2.Text = "RAM : " & ram & "%"

        If CheckConnection() = True Then
            Label3.Text = "INTERNET: CONNECTED"
        Else
            Label3.ForeColor = Color.Red
            Label3.Text = "INTERNET: DISCONNECTED"
        End If

        

    End Sub
   

    Private Function CheckConnection() As Boolean
        Dim urlobj As New Uri("https://www.google.com")
        Dim objreq As WebRequest
        objreq = WebRequest.Create(urlobj)
        Dim objrp As WebResponse
        Try
            objrp = objreq.GetResponse
            objrp.Close()
            objreq = Nothing
            Return True
        Catch ex As Exception
            Return False
        End Try
    End Function

    Private Sub Timer2_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer2.Tick
        Dim power As PowerStatus = SystemInformation.PowerStatus
        Dim percent As Single = power.BatteryLifePercent
        Label6.Text = "BATTERY: " & percent * 100 & "%"

        Timer2.Interval = 30000
    End Sub


End Class