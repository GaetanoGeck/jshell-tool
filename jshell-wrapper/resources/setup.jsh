import org.apache.commons.lang3.StringUtils
import org.apache.commons.collections4.ListUtils
import org.apache.commons.collections4.SetUtils
import org.apache.commons.csv.*

import jt.common.Config
import jt.csv.Csv
import jt.json.Json
import jt.text.Text
import static jt.collection.Coll.*

var config = new Config();
var csv = new Csv(config);
var json = new Json(config);
var text = new Text(config);
