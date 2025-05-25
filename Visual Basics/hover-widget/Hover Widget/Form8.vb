Public Class Form8



    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Form6.Label5.Text = "MODE : DEFAULT"
        Form3.BackgroundImage = My.Resources.bg
        My.Settings.bg = True
        My.Settings.gamer = False
        My.Settings.music = False
        My.Settings.blank = False
    End Sub

    Private Sub Button1_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button1.MouseHover
        Button1.BackgroundImage = My.Resources._default
    End Sub

    Private Sub Button1_MouseLeave(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button1.MouseLeave
        Button1.BackgroundImage = Nothing
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Form6.Label5.Text = "MODE : GAMER"
        Form3.BackgroundImage = My.Resources.gamingw
        My.Settings.bg = False
        My.Settings.gamer = True
        My.Settings.music = False
        My.Settings.blank = False
    End Sub

    Private Sub Button2_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button2.MouseHover
        Button2.BackgroundImage = My.Resources.gaming
    End Sub

    Private Sub Button2_MouseLeave(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button2.MouseLeave
        Button2.BackgroundImage = Nothing
    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click
        Form6.Label5.Text = "MODE : MUSIC"
        Form3.BackgroundImage = My.Resources.musicw
        My.Settings.bg = False
        My.Settings.gamer = False
        My.Settings.music = True
        My.Settings.blank = False
    End Sub

    Private Sub Button3_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button3.MouseHover
        Button3.BackgroundImage = My.Resources.music
    End Sub

    Private Sub Button3_MouseLeave(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button3.MouseLeave
        Button3.BackgroundImage = Nothing
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button4.Click
        Form6.Label5.Text = "MODE : KALI"
        Form3.BackgroundImage = My.Resources.kali
        My.Settings.bg = False
        My.Settings.gamer = False
        My.Settings.music = False
        My.Settings.blank = True
    End Sub

    Private Sub Button4_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button4.MouseHover
        Button4.BackgroundImage = My.Resources.kaliw

    End Sub

    Private Sub Button4_MouseLeave(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button4.MouseLeave
        Button4.BackgroundImage = Nothing

    End Sub

    Private Sub Form8_FormClosed(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosedEventArgs) Handles Me.FormClosed

    End Sub
    Private Sub Form8_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        My.Settings.bg = True
        My.Settings.gamer = False
        My.Settings.music = False
        My.Settings.blank = False
    End Sub
End Class