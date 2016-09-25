var gulp = require('gulp');
var rename = require('gulp-rename');
var sass = require('gulp-sass');
var webpack = require('webpack-stream');

gulp.task('styles', function () {
    gulp.src('src/main/scss/app.scss')
        .pipe(sass())
        .pipe(gulp.dest('src/main/resources/static/css'));
});

gulp.task('scripts', function () {
    gulp.src('src/main/javascript/app.js')
        .pipe(webpack({
            module: {
                loaders: [
                    {
                        loader: 'babel-loader',
                        exclude: /node_modules/,
                        query: {
                            presets: ['es2015', 'react']
                        }
                    }
                ]
            }
        }))
        .pipe(rename('app.js'))
        .pipe(gulp.dest('src/main/resources/static/js'))
    ;
});

gulp.task('default', ['scripts', 'styles']);
