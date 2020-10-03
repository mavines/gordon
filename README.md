# gordon
A Clojure library/utility for analyzing the correctness of wooden train track configurations. Often (when you let a child build) the track is designed such that trains cannot reach all points of the track or cannot change directions. The goal of Gordan is to analyze your track for optimal fun.  
Future improvements include: Usage analysis (are trains running into each other too much?), Simulation (watch your train drive around), and automatic track generation using image recognition.

Built using [Ubergraph](https://github.com/Engelberg/ubergraph), [Loom](https://github.com/aysylu/loom) , and [Quil](https://github.com/quil/quil).

## Usage
### Buliding a track
Clone and fire up ```lein repl```.
To view a track: ```(draw-track (atom data/double-loop) 500 500)```
If you are building a track and want to easily reload it while working on it:  
``` clojure
(def new-track []
(def *new-track (atom new-track))
(draw-track *new-track 500 500)
```
Edit ```new-track``` and then run ```(reset! *new-track newtrack)``` and your changes will be displayed.
(Your new-track may need to have some tiles to work, so I recommend copying an existing track in ```gordon.data``` to work from).

### Track Analysis
Once you're happy with you've built you track, turn it into an [Ubergraph](https://github.com/Engelberg/ubergraph):
```(def my-graph (track->graph my-track)```  
Some things you can learn:  
Can you change directions?  
```(uber/connected? my-graph)```

Can you get everywhere?  
```(uber/scc my-graph```
Look for having a single component. Multiple components indicates you can get stuck in one direction.

Explore [Ubergraph](https://github.com/Engelberg/ubergraph)/[Loom](https://github.com/aysylu/loom) algorithms for other interesting properties (what does shortest path tell you about your track?).

### Visualize
Since Gordon is built on [Ubergraph](https://github.com/Engelberg/ubergraph) (which is built on Loom), we can export to [GraphViz](https://graphviz.org/).
Draw using [GraphViz](https://graphviz.org/) (must be installed):
```(viz-track my-track)```

Export the [GraphViz](https://graphviz.org/) ".dot" file:  
```(track->dot my-track "my-track.dot")```


## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
