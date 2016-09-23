var gulp = require('gulp');
var rename = require('gulp-rename');
var webpack = require('webpack-stream');

gulp.task('default', function () {
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
