Public Class Form4

    Private Sub Form4_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        BackColor = Color.Black
        TransparencyKey = BackColor
        Label1.Text = DateTime.Now.ToString(" , dddd yyyy")
        Timer1.Enabled = True
        Label3.Text = "『 " & SystemInformation.UserName & " 』"
        Me.TopMost = True

    End Sub

    Private Sub Label1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Label1.Click

    End Sub

    Private Sub Label2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Label2.Click

    End Sub

    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        Label2.Text = DateAndTime.TimeOfDay.ToString("hh:mm tt")
    End Sub

    Private Sub LineShape2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles LineShape2.Click

    End Sub

   
End Class