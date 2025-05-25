Public Class HoverWidget

    Private Sub HoverWidget_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        BackColor = Color.Black
        TransparencyKey = BackColor
        Me.TopMost = True
        Dim screenWidth As Integer = Screen.PrimaryScreen.Bounds.Width
        Me.Location = New Point(screenWidth - 25, 50)
    End Sub

    Private Sub HoverWidget_FormClosing(ByVal sender As System.Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles MyBase.FormClosing
        If (e.CloseReason = CloseReason.UserClosing) Then
            e.Cancel = True
        End If
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Form2.Close()
        Form3.Close()
        Form4.Close()
        Form6.Close()
    End Sub

    Private Sub Button1_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button1.MouseHover
        Form2.Show()
        Form3.Show()
        Form4.Show()
        Form6.Show()
    End Sub

    Private Sub HoverWidget_MouseLeave(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.MouseLeave
        Form2.Close()
        Form3.Close()
        Form4.Close()
        Form6.Close()
    End Sub

    Private Sub NotifyIcon1_MouseClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles NotifyIcon1.MouseClick
        If e.Button = Windows.Forms.MouseButtons.Right Then 'Checks if the pressed button is the Right Mouse
            Form9.Show() 'Shows the Form that is the parent of "traymenu"
            Form9.Activate() 'Set the Form to "Active", that means that that will be the "selected" window
        End If
    End Sub

    Private Sub NotifyIcon1_MouseDoubleClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles NotifyIcon1.MouseDoubleClick
        Me.Show()
    End Sub
End Class
