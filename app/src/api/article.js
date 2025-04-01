import request from '@/api/request'


export const getArticleDetailList = () => {
    return request.get('/public/article/list');
};

export const getArticleDetailById = (id) => {
    return request.get(`/public/article/${id}`);
};

export const addArticleDetail = (data) => {
    return request.get(`/user/article/add`, data);
}
export const getArticleInfoList = () => {
    return request.get('/public/articleInformation/list');
};

export const getArticleInfoById = (id) => {
    return request.get(`/public/articleInformation/${id}`);
};

export const addArticleInfo = (data) => {
    return request.post('/user/articleInformation/add', data);
};