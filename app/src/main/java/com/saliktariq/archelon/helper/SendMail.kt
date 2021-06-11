package com.saliktariq.archelon.helper

import android.content.Context
import android.os.AsyncTask
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


// Kotlin version of the solution in source: https://stackoverflow.com/questions/49786771/sending-email-android-using-javamail-api
class SendMail(context: Context, email: String, subject: String, message: String) :
    AsyncTask<Void?, Void?, Void?>() {
    //Declaring Variables
    private val context: Context
    private var session: Session? = null

    //Information to send email
    private val email: String
    private val subject: String
    private val message: String





    //Class Constructor
    init {
        //Initializing variables
        this.context = context
        this.email = email
        this.subject = subject
        this.message = message
    }

    override fun doInBackground(vararg params: Void?): Void? {
        //Creating properties
        val properties = Properties()

        //Configuring properties for gmail
        properties.put("mail.smtp.host", "smtp.gmail.com")
        properties.put("mail.smtp.socketFactory.port", "465")
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        properties.put("mail.smtp.auth", "true")
        properties.put("mail.smtp.port", "465")
        //Creating a new session
        session = Session.getDefaultInstance(properties,
            object : Authenticator() {
                //Authenticating the password
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(GmailAuthDetails.EMAIL, GmailAuthDetails.PASSWORD)
                }
            })
        try {
            //Creating MimeMessage object
            val mimeMessage = MimeMessage(session)

            //Setting sender address
            mimeMessage.setFrom(InternetAddress(GmailAuthDetails.EMAIL))
            //Adding receiver
            mimeMessage.addRecipient(Message.RecipientType.TO, InternetAddress(email))
            //Adding subject
            mimeMessage.subject = subject
            //Adding message
            mimeMessage.setText(message)

            //Sending email
            Transport.send(mimeMessage)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
        return null
    }
}