package co.com.pets.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;

import co.com.pets.pojo.Mascota;


public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String queryCrearTablaMascata = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID     + " INTEGER PRIMARY KEY, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, "                              +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO   + " INTEGER, "                           +
                ConstantesBaseDatos.TABLE_MASCOTAS_LIKES  + " INTEGER "                            +
                ")";
        db.execSQL(queryCrearTablaMascata);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTAS);
        onCreate(db);
    }


    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros =db.rawQuery(query,null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(0);
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            mascotaActual.setLikes(registros.getInt(3));

            mascotas.add(mascotaActual);
        }

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){

        SQLiteDatabase db = this.getWritableDatabase();

            db.insert(ConstantesBaseDatos.TABLE_MASCOTAS,null,contentValues);


        db.close();
    }

    public void generarUpdate(Mascota mascota, int id_mascota){

        int numero_likes = mascota.getLikes();
        int like = numero_likes + 1;

        String query = "UPDATE " + ConstantesBaseDatos.TABLE_MASCOTAS +
                        " SET " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES + "=" + like +
                        " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_ID + "=" + id_mascota;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public int obtenerLikes(Mascota mascota){

        int likes = 0;
        String query = "SELECT "+ ConstantesBaseDatos.TABLE_MASCOTAS_LIKES +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            Mascota actual = new Mascota();
            likes = actual.getLikes();

        }


        return likes;
    }

    public ArrayList<Mascota> obtenerMascotaFavorita(){
        ArrayList<Mascota> likes = new ArrayList<>();
        ArrayList<Mascota> preferidas = new ArrayList<>();

        String query = "SELECT " + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ", " + ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE +
                         ", " + ConstantesBaseDatos.TABLE_MASCOTAS_FOTO +  ", " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES +
                        " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS +
                        " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES  + " > 0" +
                        " ORDER BY " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES +" DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);


        while (registros.moveToNext()){
            Mascota actual = new Mascota();
            actual.setId(0);
            actual.setNombre(registros.getString(1));
            actual.setFoto(registros.getInt(2));
            actual.setLikes(registros.getInt(3));

            likes.add(actual);
        }



        if(likes.size() > 0){
            int tamano = likes.size();

            if(tamano > 5 ) tamano = 5;

            for (int i = 0; i < tamano; i++) {
                preferidas.add(likes.get(i));
            }
        }


         return preferidas;
    }
}
