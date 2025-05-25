Public Class Form2

    Private Sub Form2_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Me.Height = Screen.PrimaryScreen.Bounds.Height
        Timer1.Enabled = True
    End Sub

    Private Sub Form2_MouseClick(ByVal sender As Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles Me.MouseClick
        Me.Close()
        Form3.Close()
        Form4.Close()
        Form6.Close()
    End Sub

    Private Sub shut_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles shut.Click
        Form7.Show()
    End Sub

    Private Sub Label1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Label1.Click
        Me.Close()
        Form3.Close()
        Form4.Close()
        Form6.Close()
        Form7.Close()
        Form8.Close()
    End Sub

    Private Sub Label1_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles Label1.MouseHover
        Label1.ForeColor = Color.Aqua
    End Sub

    Private Sub Label1_MouseLeave(ByVal sender As Object, ByVal e As System.EventArgs) Handles Label1.MouseLeave
        Label1.ForeColor = Color.White
    End Sub

    Private Sub shut_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles shut.MouseHover
        shut.BackgroundImage = My.Resources.frame_shutdown_click
    End Sub

    Private Sub shut_MouseLeave(ByVal sender As Object, ByVal e As System.EventArgs) Handles shut.MouseLeave
        shut.BackgroundImage = My.Resources.frame_shutdown1
    End Sub

    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        Me.Opacity = Me.Opacity + 0.1

        If Me.Opacity >= 1 Then
            Timer1.Enabled = False
            Me.Opacity = 1
        End If

    End Sub

    Private Sub Button1_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button1.MouseHover
        Form5.Show()
    End Sub

    Private Sub Button1_MouseLeave(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button1.MouseLeave
        Form5.Close()
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Form5.TopMost = True
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Form8.Close()
    End Sub

    Private Sub Button2_MouseHover(ByVal sender As Object, ByVal e As System.EventArgs) Handles Button2.MouseHover
        Form8.Show()
    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click
        Form10.Show()
    End Sub
End Class