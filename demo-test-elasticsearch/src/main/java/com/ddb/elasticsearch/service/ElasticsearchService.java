package com.ddb.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.search.MatchQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import com.ddb.elasticsearch.model.Article;
import com.ddb.elasticsearch.repository.ElasticsearchDemoRepository;
import com.google.common.collect.Lists;

@Service
public class ElasticsearchService {

	@Autowired
	private ElasticsearchDemoRepository elasticsearchDemoRepository;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	public void saveTest() {
		for (int i = 0; i < 40; i++) {
			Article post = new Article();
			Integer ii = i;
			post.setId(ii.longValue());
			post.setTitle(getTitle().get(i));
			post.setContent(getContent().get(i));
			post.setWeight(i);
			post.setUserId(i % 10);
			elasticsearchDemoRepository.save(post);
		}
	}

	// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	private List<String> getTitle() {
		List<String> list = new ArrayList<>();
		list.add("《如梦令·常记溪亭日暮》");
		list.add("《醉花阴·薄雾浓云愁永昼》");
		list.add("《声声慢·寻寻觅觅》");
		list.add("《永遇乐·落日熔金》");
		list.add("《如梦令·昨夜雨疏风骤》");
		list.add("《渔家傲·雪里已知春信至》");
		list.add("《点绛唇·蹴[1]罢秋千》");
		list.add("《点绛唇·寂寞深闺》");
		list.add("《蝶恋花·泪湿罗衣脂粉满》");
		list.add("《蝶恋花 离情》");
		list.add("《浣溪沙》");
		list.add("《浣溪沙》");
		list.add("《浣溪沙》");
		list.add("《浣溪沙》");
		list.add("《浣溪沙》");
		list.add("《减字木兰花·卖花担上》");
		list.add("《临江仙·欧阳公作《蝶恋花》");
		list.add("《临江仙·庭院深深深几许》");
		list.add("《念奴娇·萧条庭院》");
		list.add("《菩萨蛮·风柔日薄春犹早》");
		list.add("《菩萨蛮·归鸿声断残云碧》");
		list.add("《武陵春·风住尘香花已尽》");
		list.add("《一剪梅·红藕香残玉蕈秋》");
		list.add("《渔家傲·天接云涛连晓雾》");
		list.add("《鹧鸪天·暗淡轻黄体性柔》");
		list.add("《鹧鸪天·寒日萧萧上锁窗》");
		list.add("《一剪梅·红藕香残玉簟秋》");
		list.add("《如梦令·常记溪亭日暮》");
		list.add("《浣溪沙》");
		list.add("《浣溪沙》");
		list.add("《浣溪沙》");
		list.add("《蝶恋花·泪湿罗衣脂粉满》");
		list.add("《蝶恋花·暖日晴风初破冻》");
		list.add("《鹧鸪天·寒日萧萧上锁窗》");
		list.add("《醉花阴·薄雾浓云愁永昼》");
		list.add("《鹧鸪天·暗淡轻黄体性柔》");
		list.add("《蝶恋花·永夜恹恹欢意少》");
		list.add("《浣溪沙》");
		list.add("《浣溪沙》");
		list.add("《如梦令·谁伴明窗独坐》");
		return list;
	}

	private List<String> getContent() {
		List<String> list = new ArrayList<>();
		list.add("初中 宋·李清照 常记溪亭日暮，沉醉不知归路，兴尽晚回舟，误入藕花深处。争渡，争渡");
		list.add("重阳节 宋·李清照 薄雾浓云愁永昼，瑞脑消金兽。佳节又重阳，玉枕纱厨，半夜凉初透。东");
		list.add("闺怨诗 宋·李清照 寻寻觅觅，冷冷清清，凄凄惨惨戚戚。乍暖还寒时候，最难将息。三杯两");
		list.add("元宵节 宋·李清照 落日熔金，暮云合璧，人在何处。染柳烟浓，吹梅笛怨，春意知几许。元");
		list.add("婉约诗 宋·李清照 昨夜雨疏风骤，浓睡不消残酒，试问卷帘人，却道海棠依旧。知否，知否");
		list.add("描写梅花 宋·李清照 雪里已知春信至，寒梅点缀琼枝腻，香脸半开娇旖旎，当庭际，玉人浴出");
		list.add(" 宋·李清照 蹴罢秋千，起来慵整纤纤手。露浓花瘦，薄汗轻衣透。见客入来，袜刬金");
		list.add("闺怨诗 宋·李清照 寂寞深闺，柔肠一寸愁千缕。惜春春去。几点催花雨。倚遍阑干，只是无");
		list.add("婉约诗 宋·李清照 泪湿罗衣脂粉满。四叠阳关，唱到千千遍。人道山长水又断。萧萧微雨闻");
		list.add("描写春天 宋·李清照 暖雨晴风初破冻，柳眼梅腮，已觉春心动。酒意诗情谁与共？泪融残粉花");
		list.add("寒食节 宋·李清照 淡荡春光寒食天，玉炉沈水袅残烟，梦回山枕隐花钿。海燕未来人斗草，");
		list.add(" 宋·李清照 髻子伤春慵更梳，晚风庭院落梅初，淡云来往月疏疏，玉鸭薰炉闲瑞脑，");
		list.add(" 宋·李清照 莫许杯深琥珀浓，未成沉醉意先融。疏钟已应晚来风。瑞脑香消魂梦断，");
		list.add("闺怨诗 宋·李清照 小院闲窗春已深，重帘未卷影沉沉。倚楼无语理瑶琴，远岫出山催薄暮。");
		list.add("爱情诗 宋·李清照 绣幕芙蓉一笑开，斜偎宝鸭亲香腮，眼波才动被人猜。一面风情深有韵，");
		list.add("描写春天 宋·李清照 卖花担上，买得一枝春欲放。泪染轻匀，犹带彤霞晓露痕。怕郎猜道，奴");
		list.add("》 宋·李清照 欧阳公作《蝶恋花》，有“深深深几许”之句，予酷爱之。用其语作“庭");
		list.add("描写梅花 宋·李清照 庭院深深深几许，云窗雾阁春迟，为谁憔悴损芳姿。夜来清梦好，应是发");
		list.add("寒食节 宋·李清照 萧条庭院，又斜风细雨，重门须闭。宠柳娇花寒食近，种种恼人天气。险");
		list.add("思乡诗 宋·李清照 风柔日薄春犹早，夹衫乍著心情好。睡起觉微寒，梅花鬓上残。故乡何处");
		list.add("描写春天 宋·李清照 归鸿声断残云碧，背窗雪落炉烟直。烛底凤钗明，钗头人胜轻。角声催晓");
		list.add("闺怨诗 宋·李清照 风住尘香花已尽，日晚倦梳头。物是人非事事休，欲语泪先流。闻说双溪");
		list.add(" 宋·李清照 红藕香残玉蕈秋，轻解罗裳，独上兰舟。云中谁寄锦书来？雁字回时，月");
		list.add("豪放诗 宋·李清照 天接云涛连晓雾。星河欲转千帆舞。仿佛梦魂归帝所。闻天语。殷勤问我");
		list.add("描写花 宋·李清照 暗淡轻黄体性柔。情疏迹远只香留。何须浅碧深红色，自是花中第一流。");
		list.add("描写秋天 宋·李清照 寒日萧萧上琐窗，梧桐应恨夜来霜。酒阑更喜团茶苦，梦断偏宜瑞脑香。");
		list.add("闺怨诗 宋·李清照 红藕香残玉簟秋。轻解罗裳，独上兰舟。云中谁寄锦书来？雁字回时，月");
		list.add(" 宋·李清照 常记溪亭日暮。沈醉不知归路。兴尽晚回舟，误入藕花深处。争渡。争渡");
		list.add(" 宋·李清照 莫许杯深琥珀浓。未成沈醉意先融。已应晚来风。瑞脑香消魂梦断，");
		list.add(" 宋·李清照 小院闲窗春色深。重帘未卷影沈沈。倚楼无语理瑶琴。远岫出山催薄暮，");
		list.add(" 宋·李清照 淡荡春光寒食天。玉炉沈水袅残烟。梦回山枕隐花钿。海燕未来人斗草，");
		list.add(" 宋·李清照 泪湿罗衣脂粉满。四叠阳关，唱到千千遍。人道山长山又断。萧萧微雨闻");
		list.add(" 宋·李清照 暖日晴风初破冻。柳眼眉腮，已觉春心动。酒意诗情谁与共。泪融残粉花");
		list.add(" 宋·李清照 寒日萧萧上锁窗。梧桐应恨夜来霜。酒阑更喜团茶苦，梦断偏宜瑞脑香。");
		list.add(" 宋·李清照 薄雾浓云愁永昼。瑞脑消金兽。佳节又重阳，玉枕纱厨，半夜凉初透。东");
		list.add(" 宋·李清照 暗淡轻黄体性柔。情疏迹远只香留。何须浅碧深红色，自是花中第一流。");
		list.add(" 宋·李清照 永夜恹恹欢意少。空梦长安，认取长安道。为报今年春色好。花光月影宜");
		list.add(" 宋·李清照 髻子伤春慵更梳。晚风庭院落梅初。淡云来往月疏疏。玉鸭熏炉闲瑞脑，");
		list.add(" 宋·李清照 绣面芙蓉一笑开。斜飞宝鸭衬香腮。眼波才动被人猜。一面风情深有韵，");
		list.add(" 宋·李清照 谁伴明窗独坐，我共影儿俩个。灯尽欲眠时，影也把人抛躲。无那，无那");
		return list;
	}

	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&新大陆新大陆新大陆新大陆&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	
    /**
     * 使用elasticsearchTemplate
     * 使用QueryBuilder
     * termQuery("key", obj) 完全匹配
     * termsQuery("key", obj1, obj2..)   一次匹配多个值
     * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
     * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
     * matchAllQuery();         匹配所有文件
     */
	
	/**
		* @throws
		* @Description:
		* @return
		*/ 
	public List<Article> findQuery(String keyword){
		SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.termQuery("title", keyword));
		SearchQuery searchQuery5 = new NativeSearchQuery(QueryBuilders.termsQuery("title", "浣溪","接云"));
		SearchQuery searchQuery1 = new NativeSearchQuery(QueryBuilders.termQuery("title", true));
		SearchQuery searchQuery2 = new NativeSearchQuery(QueryBuilders.termQuery("title", 0.0));
		SearchQuery searchQuery3= new NativeSearchQuery(QueryBuilders.termQuery("title", 0));
		SearchQuery searchQuery4= new NativeSearchQuery(QueryBuilders.termQuery("title", true));
		SearchQuery searchQuery6= new NativeSearchQuery(QueryBuilders.matchQuery("title", keyword));
		SearchQuery searchQuery7= new NativeSearchQuery(QueryBuilders.multiMatchQuery("title", keyword,"沙"));
		SearchQuery searchQuery8= new NativeSearchQuery(QueryBuilders.matchAllQuery());
		List<Article> list = elasticsearchTemplate.queryForList(searchQuery, Article.class);
		List<Article> list1 = elasticsearchTemplate.queryForList(searchQuery5, Article.class);
		List<Article> list2 = elasticsearchTemplate.queryForList(searchQuery7, Article.class);
		List<Article> list3 = elasticsearchTemplate.queryForList(searchQuery8, Article.class);
		List<Article> queryForList = elasticsearchTemplate.queryForList(searchQuery6, Article.class);
		return list;
		
		
	}

	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&新大陆新大陆新大陆新大陆&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	
	/**
	 * @throws @Description:保存数据到索引库
	 * @param article
	 */
	public void save(Article article) {
		elasticsearchDemoRepository.save(article);
	}

	/**
	 * @throws @Description:搜索索引库的关键字
	 * @param queryString
	 * @return
	 */
	public List<Article> search(String queryString) {
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
		Iterable<Article> search = elasticsearchDemoRepository.search(builder);
		search.forEach(art -> System.err.println(art.toString()));
		List<Article> list = Lists.newArrayList();
		// elasticsearchDemoRepository.searchSimilar(entity, fields, pageable)
		return list;
	}

	
	/**
	 * @param keyword 
	 * @return 
	 * @throws @Description:根据搜索关键词查询数据
	 */
	public List<Article> queryTest(String keyword) {
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(keyword);
		Iterable<Article> search = elasticsearchDemoRepository.search(builder);
		List<Article> list = new ArrayList<>();
		search.forEach(a->list.add(a));
		///
		SearchQuery sQuery = new NativeSearchQuery(builder);
		List<Article> queryForList = elasticsearchTemplate.queryForList(sQuery, Article.class);//查询无结果
		///
		SearchQuery sq = new NativeSearchQueryBuilder().withQuery(builder).withPageable(new PageRequest(0, 20, new Sort(Direction.DESC, "weight"))).build();
		List<Article> ll = elasticsearchTemplate.queryForList(sq, Article.class);
		///
		MatchQuery matchQuery = new MatchQuery(null);
		MatchQueryBuilder queryBuilder = new MatchQueryBuilder("content", keyword);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
		List<Article> queryForList2 = elasticsearchTemplate.queryForList(searchQuery, Article.class);
		///
		CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria(keyword));
		List<Article> queryForList3 = elasticsearchTemplate.queryForList(criteriaQuery, Article.class);
		///
		String[] field = {"title","content"};
		Article aaa = new Article();
 		List<Article> content = elasticsearchDemoRepository.searchSimilar(aaa,field, new PageRequest(0, 10)).getContent();
 		test();
 		Iterable<Article> search2 = elasticsearchDemoRepository.search(new QueryStringQueryBuilder(" spring boot OR 浣溪沙"));
 		System.err.println("[");
 		search2.forEach(aa->System.err.println(aa.toString()));
 		System.err.println("]");
 		test1();
 		test2();
		return ll;
	}
	
    @Test
    public void test() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(new QueryStringQueryBuilder("落日熔金")).withPageable(new PageRequest(0, 50)).build();
        List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
        for (Article article : articles) {
            System.out.println(article.toString());
        }
    }

    @Test
    public void test1() {
    	 SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("content", "浣溪沙")).withPageable(new PageRequest(0, 50)).build();
    	List<Article> queryForList2 = elasticsearchTemplate.queryForList(searchQuery, Article.class);
    	if (queryForList2!=null && !queryForList2.isEmpty()) {
    		queryForList2.forEach(allO->System.err.println(allO.toString()));
		}
	}
    
    @Test
    public void test2() {
    	SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("content", "落日熔金")).build();
    	List<Article> queryForList2 = elasticsearchTemplate.queryForList(searchQuery, Article.class);
    	System.err.println("ppppppppppppppppppppppppppppppppppppp");
    	queryForList2.forEach(allO->System.err.println(allO.toString()));
    }
}
