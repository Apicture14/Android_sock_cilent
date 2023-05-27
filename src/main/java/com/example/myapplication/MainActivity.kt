package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket
import org.json.JSONObject
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.SocketAddress
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var hin: EditText? = null
    var pin: EditText? = null
    var btn_c: Button? = null
    var btn_d: Button? = null
    var opt: EditText? = null
    var min: EditText? = null
    var btn_s: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hin = findViewById<EditText>(R.id.Hin)
        pin = findViewById<EditText>(R.id.Pin)
        btn_c = findViewById<Button>(R.id.btn_Conn)
        btn_d = findViewById<Button>(R.id.btn_DisConn)
        opt = findViewById<EditText>(R.id.editTextTextMultiLine)
        min = findViewById(R.id.Min)
        btn_s = findViewById(R.id.button_send)

        Bind()

        opt!!.movementMethod = ScrollingMovementMethod.getInstance()

        val sock = DatagramSocket()
        sock.bind(InetSocketAddress(R.string.default_lhost.toString(), R.string.default_lport))
        //opt!!.scrollBarSize = 20
    }

    fun Send(): Socket? {

        try {
            val host = hin!!.text.toString()
            val port: Int = (findViewById<TextView>(R.id.Pin)).text.toString().toInt()
            val sock = DatagramSocket()

            val target = InetSocketAddress(host,port)

            var outpack = DatagramPacket(ByteArray(0),0,target)




        } catch (ex: Exception) {
            log(ex.toString())
            //log(ex.message!!)
            return null
        }
    }

    fun ConnMain(sock: Socket): Unit {
        try {
            if (sock == null) {
                log("invalid socket")
            }
            val os = sock.getOutputStream()
            log("success")

            sock.close()
        } catch (ex: Exception) {
            opt!!.append(ex.message)
        }
    }

    fun log(msg: String): Unit {
        val t = LocalTime.from(LocalTime.now())
        opt!!.append("[${t.hour}:${t.minute}:${t.second}] ${msg}\n")
    }

    fun Bind() {
        btn_c!!.setOnClickListener {
            Thread(Runnable {
                try {

                    var conn = GetConn()
                    if (conn == null) {
                        log("returned")
                    } else {
                        ConnMain(conn)
                    }
                }catch (ex:Exception){
                    log(ex.message.toString())
                }
            }).start()
        }


    }
}

