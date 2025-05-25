Public Class Form5

    Private Sub Form5_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Label2.Text = My.Settings.name
        Label3.Text = My.Settings.age
        Label4.Text = My.Settings.anything
        Label5.Text = My.Settings.moto
    End Sub

    Private Sub PictureBox1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles PictureBox1.Click

    End Sub

End Class