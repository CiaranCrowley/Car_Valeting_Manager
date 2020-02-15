package ie.wit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import ie.wit.R
import kotlinx.android.synthetic.main.activity_sign_in.*

//https://www.youtube.com/watch?v=ZC2w2iQQOdo

const val RC_SIGN_IN = 123

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        //Sign In Button
        sign_in_button.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

            val intent = Intent(this, BookingListActivity::class.java)
            startActivity(intent)
        }

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {

        }
    }
}
