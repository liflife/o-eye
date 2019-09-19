@Service
public class OrderServiceAPI{

    private Logger logger = LoggerFactory.getLogger(OrderServiceAPI.class);

    @Autowired
    private OrderService orderService;
    

    /**
     * 增加调用方基本信息
     * @param id
     * @return
     */
    @Override
    public User selectByPrimaryKey(Long id) {
        // 本端是否为提供端，这里会返回true
        boolean isProviderSide = RpcContext.getContext().isProviderSide();
        // 获取调用方IP地址
        String clientIp = RpcContext.getContext().getRemoteHost();
        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        String url = RpcContext.getContext().getUrl().toFullString();

        logger.info("{} {} {}", isProviderSide, clientIp, url);
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
