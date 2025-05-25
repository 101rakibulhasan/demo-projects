Public Class Form10

    Private Sub LinkLabel1_LinkClicked(ByVal sender As System.Object, ByVal e As System.Windows.Forms.LinkLabelLinkClickedEventArgs) Handles LinkLabel1.LinkClicked
        

        Process.Start("https://cybergen0.blogspot.com/")
        Form2.Close()
        Form3.Close()
        Form4.Close()
        Form5.Close()
        Form6.Close()
        Form7.Close()
        Form8.Close()
        Form9.Close()
        Me.Close()
    End Sub

    Private Sub Form10_FormClosed(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosedEventArgs) Handles Me.FormClosed
        My.Settings.name = TextBox1.Text
        My.Settings.age = TextBox2.Text
        My.Settings.anything = TextBox3.Text
        My.Settings.moto = TextBox4.Text
    End Sub

    
    Private Sub Form10_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        TextBox1.Text = My.Settings.name
        TextBox2.Text = My.Settings.age
        TextBox3.Text = My.Settings.anything
        TextBox4.Text = My.Settings.moto
    End Sub
End Class