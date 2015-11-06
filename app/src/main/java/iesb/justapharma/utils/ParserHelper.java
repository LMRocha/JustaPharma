package iesb.justapharma.utils;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import iesb.justapharma.domain.Estabelecimento;
import iesb.justapharma.domain.Medicamento;

/**
 * Created by SAMSUNG on 05/11/2015.
 */
public class ParserHelper extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Medicamento.class);
        ParseObject.registerSubclass(Estabelecimento.class);
        Parse.initialize(this, "iH9pUoflZiwuMnrPWOHkg0GYCa9OQ4zviMcdTMuV", "wPqzasyybYrdogqqIRRg5ZRef7zAiCqhDe8phZ82");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo","bar");
        testObject.saveInBackground();
    }
}
