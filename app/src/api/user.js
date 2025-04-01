import request from '@/api/request'


export const getUserProfile = () => {
    return request.get('/user/profile');
};

export const updateUserProfile = (data) => {
    return request.put('/user/profile', data);
};

export const getUserDetails = (id) => {
    return request.get(`/public/userDetail/${id}`);
};

export const addUserDetail = (data) => {
    return request.post('/public/userDetail', data);
};

export const logout = () => {
    return request.post('/auth/logout');
};