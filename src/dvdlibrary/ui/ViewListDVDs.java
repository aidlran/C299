package dvdlibrary.ui;

import dvdlibrary.dto.DVD;
import io.UserIO;

public class ViewListDVDs extends ViewImpl {

    protected DVD[] dvdList;

    public ViewListDVDs(UserIO io, DVD[] dvdList) {
        super(io);
        this.dvdList = dvdList;
    }

    @Override
    public String getTitle() {
        return "All DVDs";
    }

    @Override
    public void render() {

        super.render();

        io.print("");

        for (DVD dvd : dvdList)
            io.print(dvd.getMovieTitle());
    }
}
