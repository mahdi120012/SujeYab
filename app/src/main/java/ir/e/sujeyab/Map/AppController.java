package ir.e.sujeyab.Map;

import android.app.Application;

import ir.map.sdk_map.Mapir;

public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //TODO Please add your API_KEY
        //Mapir.init(getBaseContext(), "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjI5ZDcxNmRlMWM4YTdlYjcwNGU2ZjA0ZTNiNWFkZTg0OTc4YzQ4ZjMzNGNlMTg0NmE3MDRhZjBjM2ZmOWI5NGQ4YzM5YjRkZTk0YTgyYWNjIn0.eyJhdWQiOiIxMzE1MiIsImp0aSI6IjI5ZDcxNmRlMWM4YTdlYjcwNGU2ZjA0ZTNiNWFkZTg0OTc4YzQ4ZjMzNGNlMTg0NmE3MDRhZjBjM2ZmOWI5NGQ4YzM5YjRkZTk0YTgyYWNjIiwiaWF0IjoxNjE1Mzc4ODUwLCJuYmYiOjE2MTUzNzg4NTAsImV4cCI6MTYxNzg4MDg1MCwic3ViIjoiIiwic2NvcGVzIjpbImJhc2ljIl19.h76KEl_t6uN8JAmRFQpmcErnPYyU2qGKWIGZTd7e2tk0nM1sxtwcj2x1EZg8D-hOPcU4N2N0S0LrABS2K77zkPu0LYI6ZsiuX06vzyd0pWkWLRQa7MuCbFAtFd9ZRtfcEVUz3E0U4E_kSqvhXw9hBFAzVWE37RTMwrsNVISYO9fGeL5MPVlIz1xtLbm_KiSmmYvBRrgSsNNYPgTAspmYynZpHiiibM4u_fBcJ93IsYhOy9TtNRSrYkRSKyQcDRs5leZQ_0zKvaaWLIUE9d7MGMwz4lrj0ISkHbVbHYbOYdcxllrSZ4Hbu2WftfMk3gwXgxrMT6ArihSOXnnwx5g4lQ");
        Mapir.getInstance(this, "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjI5ZDcxNmRlMWM4YTdlYjcwNGU2ZjA0ZTNiNWFkZTg0OTc4YzQ4ZjMzNGNlMTg0NmE3MDRhZjBjM2ZmOWI5NGQ4YzM5YjRkZTk0YTgyYWNjIn0.eyJhdWQiOiIxMzE1MiIsImp0aSI6IjI5ZDcxNmRlMWM4YTdlYjcwNGU2ZjA0ZTNiNWFkZTg0OTc4YzQ4ZjMzNGNlMTg0NmE3MDRhZjBjM2ZmOWI5NGQ4YzM5YjRkZTk0YTgyYWNjIiwiaWF0IjoxNjE1Mzc4ODUwLCJuYmYiOjE2MTUzNzg4NTAsImV4cCI6MTYxNzg4MDg1MCwic3ViIjoiIiwic2NvcGVzIjpbImJhc2ljIl19.h76KEl_t6uN8JAmRFQpmcErnPYyU2qGKWIGZTd7e2tk0nM1sxtwcj2x1EZg8D-hOPcU4N2N0S0LrABS2K77zkPu0LYI6ZsiuX06vzyd0pWkWLRQa7MuCbFAtFd9ZRtfcEVUz3E0U4E_kSqvhXw9hBFAzVWE37RTMwrsNVISYO9fGeL5MPVlIz1xtLbm_KiSmmYvBRrgSsNNYPgTAspmYynZpHiiibM4u_fBcJ93IsYhOy9TtNRSrYkRSKyQcDRs5leZQ_0zKvaaWLIUE9d7MGMwz4lrj0ISkHbVbHYbOYdcxllrSZ4Hbu2WftfMk3gwXgxrMT6ArihSOXnnwx5g4lQ");
    }
}