/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author desousar
 */
public class Manga {
    private String pk_manga;
    private String nomDuManga;
    private String nomDuTome;
    private String numeroDuTome;

    public String getPk_manga() {
        return pk_manga;
    }

    public void setPk_manga(String pk_manga) {
        this.pk_manga = pk_manga;
    }

    public String getNumeroDuTome() {
        return numeroDuTome;
    }

    public void setNumeroDuTome(String numeroDuTome) {
        this.numeroDuTome = numeroDuTome;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    private String image;

    public Manga() {
    }

    public String getNomDuManga() {
        return nomDuManga;
    }

    public void setNomDuManga(String nomManga) {
        this.nomDuManga = nomManga;
    }

    public String getNomDuTome() {
        return nomDuTome;
    }

    public void setNomDuTome(String nomTome) {
        this.nomDuTome = nomTome;
    }
    
    
    
}
