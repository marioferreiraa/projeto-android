package com.example.mybookcase.data;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mybookcase.R;
import com.example.mybookcase.data.model.Item;
import com.example.mybookcase.data.persistence.ItemDAO;

import java.io.ByteArrayOutputStream;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragmento, new FragmentoHomeDashboard()).commit();

        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        /*=========================Testes de inserir itens==========================================*/
        ItemDAO itemDAO = new ItemDAO(getApplicationContext());

        Bitmap imgDefault = BitmapFactory.decodeResource(getResources(), R.drawable.hp3);
        byte[] imgParsed = getBytesFromBitmap(imgDefault);
        Item item1 = new Item(null,"Harry Potter e o prisioneiro de azkaban",
                "Conheça Harry Potter, um menino que soube em seu aniversário de onze anos que é filho órfão de dois bruxos e possui poderes mágicos únicos. De filho indesejado, passa a ser um estudante de Hogwarts, uma escola inglesa para bruxos. Lá ele conhece vários amigos que o ajudam a descobrir a verdade sobre as mortes misteriosas de seus pais.",
                "movie",
                "s",
                imgParsed);

        imgDefault = BitmapFactory.decodeResource(getResources(), R.drawable.hp1);
        imgParsed = getBytesFromBitmap(imgDefault);
        Item item2 = new Item(null,"Harry Potter e a pedra filosofal",
                "Conheça Harry Potter, um menino que soube em seu aniversário de onze anos que é filho órfão de dois bruxos e possui poderes mágicos únicos. De filho indesejado, passa a ser um estudante de Hogwarts, uma escola inglesa para bruxos. Lá ele conhece vários amigos que o ajudam a descobrir a verdade sobre as mortes misteriosas de seus pais.",
                "movie",
                "s",
                imgParsed);

        imgDefault = BitmapFactory.decodeResource(getResources(), R.drawable.got1);
        imgParsed = getBytesFromBitmap(imgDefault);
        Item item3 = new Item(null,"Game of Thrones",
                "Quando Eddard Stark, lorde do castelo de Winterfell, aceita a prestigiada posição de Mão do Rei oferecida pelo velho amigo, o rei Robert Baratheon, não desconfia que sua vida está prestes a ruir em sucessivas tragédias. Sabe-se que Lorde Stark aceitou a proposta porque desconfia que o dono anterior do título fora envenenado pela manipuladora rainha - uma cruel mulher do clã Lannister. E sua intenção é proteger o rei. Mas ter como inimigo os Lannister pode ser fatal: a ambição dessa família pelo poder parece não ter limites e o rei corre grande perigo. Agora, sozinho na corte, Eddard percebe que não só o rei está em apuros, mas também ele e toda a sua família. Quem vencerá a guerra dos tronos?",
                "book",
                "s",
                imgParsed);

        imgDefault = BitmapFactory.decodeResource(getResources(), R.drawable.got3);
        imgParsed = getBytesFromBitmap(imgDefault);
        Item item4 = new Item(null,"A Tormenta de Espadas",
                "Enquanto os Sete Reinos estremecem com a chegada dos temíveis selvagens, que atravessam a Muralha numa maré interminável de homens, gigantes e terríveis bestas, Jon Snow, o Bastardo de Winterfell, que se encontra entre eles, divide-se entre sua consciência e o papel que é forçado a desempenhar. ",
                "book",
                "s",
                imgParsed);

        //itemDAO.insertItem(item1);
        //itemDAO.insertItem(item2);
        //itemDAO.insertItem(item3);
        //itemDAO.insertItem(item4);
        //itemDAO.getItens();
        /*=========================Testes de inserir itens==========================================*/
    }

    @Override
    public void onResume(){
        super.onResume();
        FragmentoAcervoFilmes frag = new FragmentoAcervoFilmes();
        Toast.makeText(HomeActivity.this,"Resumindo, andioasujd",Toast.LENGTH_SHORT);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = new FragmentoHomeDashboard();
        } else if (id == R.id.navs_acervo_livros) {
            fragment = new FragmentoAcervoLivros();
        } else if (id == R.id.nav_acervo_filmes) {
            fragment = new FragmentoAcervoFilmes();
        } else if(id == R.id.nav_livros_futuros){
            fragment = new FragmentoIndicLivros();
        }else if(id == R.id.nav_filmes_futuros){
            fragment = new FragmentoIndicFIlmes();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragmento, fragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

}
