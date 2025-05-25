Public Class Form3

    Private Sub Form3_FormClosed(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosedEventArgs) Handles Me.FormClosed
    End Sub

    Private Sub Form3_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Timer1.Enabled = True
       

        If My.Settings.bg = True Then
            Form6.Label5.Text = "MODE : DEFAULT"
            Me.BackgroundImage = My.Resources.bg
        End If

        If My.Settings.gamer = True Then
            Form6.Label5.Text = "MODE : GAMER"
            Me.BackgroundImage = My.Resources.gamingw
        End If

        If My.Settings.music = True Then
            Form6.Label5.Text = "MODE : MUSIC"
            Me.BackgroundImage = My.Resources.musicw
        End If

        If My.Settings.blank = True Then
            Form6.Label5.Text = "MODE : BLANK"
            Me.BackgroundImage = My.Resources.kali
        End If
    End Sub
    Private Sub Form3_FormClosing(ByVal sender As System.Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles MyBase.FormClosing
        If (e.CloseReason = CloseReason.UserClosing) Then
            Form7.Close()
            Form8.Close()
            Form4.Close()
            Form6.Close()
            Form2.Close()
        End If
    End Sub

    Private Sub Form3_MouseClick(ByVal sender As Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles Me.MouseClick
        Me.Close()
        Form7.Close()
        Form4.Close()
        Form6.Close()
        Form2.Close()
        Form8.Close()
    End Sub

    Private Sub Form3_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.MouseHover
        Me.TopMost = False
    End Sub

    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        Me.Opacity = Me.Opacity + 0.1

        If Me.Opacity >= 1 Then
            Timer1.Enabled = False
            Me.Opacity = 1
        End If

    End Sub
End Class